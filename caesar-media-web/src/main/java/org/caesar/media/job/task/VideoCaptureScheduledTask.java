package org.caesar.media.job.task;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.caesar.crawler.live.netty.base.enums.PublishTimeType;
import org.caesar.crawler.live.netty.base.enums.SearchChannelType;
import org.caesar.crawler.live.netty.base.enums.SearchSortType;
import org.caesar.media.service.DouyinService;
import org.caesar.media.entity.JobConfig;
import org.caesar.media.entity.JobResource;
import org.caesar.media.enums.PlatformType;
import org.caesar.media.job.JobTask;
import org.caesar.media.service.JobConfigService;
import org.caesar.media.service.JobResourceService;
import org.springframework.stereotype.Component;

/**
 * @author peng.guo
 */
@Log4j2
@Component("VIDEO_CAPTURE")
@RequiredArgsConstructor
public class VideoCaptureScheduledTask implements JobTask {

    private final JobConfigService jobConfigService;
    private final JobResourceService jobResourceService;
    private final DouyinService douyinService;


    @Override
    public void run(JobConfig jobConfig) {
        if (jobConfig.getStatus() != 1) {
            return;
        }
        log.info("开始执行任务:{}", jobConfig.getTitle());
        String params = jobConfig.getParams();
        JSONObject jsonObject = new JSONObject(params);
        String keyword = jsonObject.getStr("keyword");
        if (StrUtil.isBlank(keyword)) {
            log.error("任务参数错误:{},测试没有检索关键字", jobConfig.getTitle());
        } else {
            int offset = jsonObject.getInt("offset");
            int count = jsonObject.getInt("count");
            if (count == 0) {
                count = 10;
            }
            PublishTimeType publishTimeType;
            if (StrUtil.isBlank(jsonObject.getStr("publishTimeType"))) {
                publishTimeType = PublishTimeType.UNLIMITED;
            } else {
                publishTimeType = PublishTimeType.fromValue(jsonObject.getStr("publishTimeType"));
            }
            SearchChannelType searchChannelType;
            if (StrUtil.isBlank(jsonObject.getStr("searchChannelType"))) {
                searchChannelType = SearchChannelType.GENERAL;
            } else {
                searchChannelType = SearchChannelType.fromValue(jsonObject.getStr("searchChannelType"));
            }
            SearchSortType searchSortType;
            if (StrUtil.isBlank(jsonObject.getStr("searchSortType"))) {
                searchSortType = SearchSortType.GENERAL;
            } else {
                searchSortType = SearchSortType.fromValue(jsonObject.getStr("searchSortType"));
            }
            try {
                cn.hutool.json.JSONObject queryKeyWord = douyinService.queryKeyWord(keyword, offset, count, publishTimeType, searchChannelType, searchSortType);
                if (queryKeyWord != null) {
                    JSONArray data = queryKeyWord.getJSONArray("data");
                    if (data.isEmpty()) {
                        log.info("没有数据");
                        jobConfigService.updateStatus(jobConfig.getId(), 3);
                        return;
                    }
                    for (Object datum : data) {
                        JSONObject meta = (JSONObject) datum;
                        JSONObject awemeInfo = meta.getJSONObject("aweme_info");
                        String awemeId = awemeInfo.getStr("aweme_id");
                        JSONObject author = awemeInfo.getJSONObject("author");
                        JobResource jobResource = new JobResource();
                        jobResource.setResourceId(awemeId);
                        jobResource.setJobConfigId(jobConfig.getId());
                        jobResource.setPlatformCode(PlatformType.DOUYIN.getValue());
                        jobResource.setResourceType("video");
                        jobResource.setTitle(awemeInfo.getStr("desc"));
                        jobResource.setAuthor(author.toString());
                        jobResource.setMeta(meta.toString());
                        jobResourceService.saveByResourceId(awemeId, jobResource);
                    }
                }
            } catch (Exception e) {
                log.error("抓取异常:{}", e.getMessage(), e);
            }
            JSONObject param = new JSONObject();
            offset=offset+10;
            param.put("offset", offset);
            param.put("count", count);
            param.put("publishTimeType", publishTimeType.getValue());
            param.put("searchChannelType", searchChannelType.getValue());
            param.put("searchSortType", searchSortType.getValue());
            param.put("keyword", keyword);
            JobConfig jobConfigUpdate = new JobConfig();
            jobConfigUpdate.setId(jobConfig.getId());
            jobConfigUpdate.setParams(param.toString());
            jobConfigService.updateById(jobConfigUpdate);

        }
    }
}
