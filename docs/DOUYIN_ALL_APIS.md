# 抖音API接口完整实现文档

基于已有的 `getAwemePost` 成功实现，现在为你的项目新增了4个重要的抖音API接口，完全参考 MediaCrawler 的实现方式。

## 🎯 新增接口概览

| 接口 | 功能 | 核心参数 |
|------|------|----------|
| `/aweme/v1/web/aweme/detail/` | 获取视频详情 | `aweme_id` |
| `/aweme/v1/web/comment/list/` | 获取评论列表 | `aweme_id`, `cursor`, `count` |
| `/aweme/v1/web/comment/list/reply/` | 获取评论回复 | `aweme_id`, `comment_id`, `cursor` |
| `/aweme/v1/web/user/profile/other/` | 获取用户信息 | `sec_user_id` |

## 📡 HTTP接口说明

### 1. 获取视频详情

```http
POST /douyin/aweme-detail
Content-Type: application/json

{
    "awemeId": "7123456789012345678",
    "userId": 123
}
```

**响应示例：**
```json
{
    "success": true,
    "data": {
        "status_code": 0,
        "aweme_detail": {
            "aweme_id": "7123456789012345678",
            "desc": "视频描述",
            "create_time": 1691234567,
            "statistics": {
                "digg_count": 1000,
                "comment_count": 50,
                "play_count": 5000
            },
            "video": {
                "duration": 15000
            }
        }
    }
}
```

### 2. 获取评论列表

```http
POST /douyin/comment-list
Content-Type: application/json

{
    "awemeId": "7123456789012345678",
    "cursor": 0,
    "count": 20,
    "userId": 123
}
```

**响应示例：**
```json
{
    "success": true,
    "data": {
        "status_code": 0,
        "comments": [
            {
                "cid": "comment_123",
                "text": "评论内容",
                "user": {
                    "nickname": "用户昵称"
                },
                "digg_count": 10
            }
        ],
        "cursor": 1691234567000,
        "has_more": 1
    }
}
```

### 3. 获取评论回复

```http
POST /douyin/comment-reply
Content-Type: application/json

{
    "awemeId": "7123456789012345678",
    "commentId": "comment_123456",
    "cursor": 0,
    "count": 20,
    "userId": 123
}
```

### 4. 获取用户信息

```http
POST /douyin/user-profile
Content-Type: application/json

{
    "secUserId": "MS4wLjABAAAAxxxxxxxxxxx",
    "userId": 123
}
```

**响应示例：**
```json
{
    "success": true,
    "data": {
        "status_code": 0,
        "user": {
            "sec_uid": "MS4wLjABAAAAxxx",
            "nickname": "用户昵称",
            "avatar_thumb": "头像URL",
            "follower_count": 10000,
            "following_count": 100,
            "aweme_count": 50
        }
    }
}
```

### 5. 获取用户视频帖子（已实现）

```http
POST /douyin/aweme-post
Content-Type: application/json

{
    "secUserId": "MS4wLjABAAAAxxxxxxxxxxx",
    "maxCursor": 0,
    "count": 18,
    "userId": 123
}
```

## 🔧 参数说明

### 通用参数
- `userId`: 可选，指定用户池中的用户ID，使用特定用户的session
- 所有接口都支持带Cookie和不带Cookie两种模式

### 视频详情参数
- `awemeId`: 视频ID，从视频分享链接或其他接口获取

### 评论相关参数
- `cursor`: 分页游标，首次请求传0，后续使用响应中的cursor
- `count`: 每页数量，评论默认20
- `commentId`: 评论ID，获取评论回复时需要

### 用户信息参数
- `secUserId`: 用户唯一标识，从用户页面URL获取

## 💻 Java调用示例

```java
// 注入服务
@Autowired
private DouyinService douyinService;

// 1. 获取视频详情
JSONObject detail = douyinService.getAwemeDetail("7123456789", null);

// 2. 获取评论列表
JSONObject comments = douyinService.getCommentList("7123456789", 0L, 20, null);

// 3. 获取评论回复
JSONObject replies = douyinService.getCommentReply("7123456789", "comment_123", 0L, 20, null);

// 4. 获取用户信息
JSONObject userInfo = douyinService.getUserProfile("MS4wLjABAAAAxxx", null);

// 5. 获取用户视频
JSONObject userVideos = douyinService.getAwemePost("MS4wLjABAAAAxxx", 0L, 18, null);
```

## 🧪 测试方式

### 1. 使用测试工具类
```java
// 运行完整API测试
org.caesar.media.test.DouyinAllApisTestRunner.main()
```

### 2. curl 测试
```bash
# 测试视频详情
curl -X POST http://localhost:8080/douyin/aweme-detail \
  -H "Content-Type: application/json" \
  -d '{"awemeId": "7123456789"}'

# 测试评论列表
curl -X POST http://localhost:8080/douyin/comment-list \
  -H "Content-Type: application/json" \
  -d '{"awemeId": "7123456789", "cursor": 0, "count": 20}'
```

## 🏗️ 技术架构

### 分层设计
1. **Controller层**: 接收HTTP请求，参数验证
2. **Service层**: 业务逻辑，重试机制，用户池管理
3. **Client层**: 封装API调用，支持多种Cookie模式
4. **Codec层**: 参数构建，签名生成，HTTP请求

### 关键特性
- ✅ **完整参数验证**: 使用Java Bean Validation
- ✅ **自动重试**: 最多5次，指数退避
- ✅ **用户池支持**: 支持指定用户session
- ✅ **签名算法**: 使用JavaScript生成a_bogus签名
- ✅ **错误处理**: 统一异常处理和错误响应

## 🔍 获取必要参数

### 获取 aweme_id (视频ID)
1. 打开抖音网页版视频页面
2. 查看URL：`https://www.douyin.com/video/7123456789012345678`
3. 数字部分就是 `aweme_id`

### 获取 comment_id (评论ID)
1. 通过评论列表接口获取
2. 在响应的 `comments` 数组中的 `cid` 字段

### 获取 sec_user_id (用户ID)
1. 访问用户主页：`https://www.douyin.com/user/MS4wLjABAAAAxxx`
2. `user/` 后面的字符串就是 `sec_user_id`

## 🚀 完整的抖音数据获取流程

```java
// 1. 先获取用户信息
JSONObject userInfo = douyinService.getUserProfile(secUserId, null);

// 2. 获取用户的所有视频
JSONObject userVideos = douyinService.getAwemePost(secUserId, 0L, 18, null);

// 3. 对每个视频获取详情
String awemeId = userVideos.getJSONArray("aweme_list").getJSONObject(0).getStr("aweme_id");
JSONObject videoDetail = douyinService.getAwemeDetail(awemeId, null);

// 4. 获取视频的评论
JSONObject comments = douyinService.getCommentList(awemeId, 0L, 20, null);

// 5. 获取评论的回复
String commentId = comments.getJSONArray("comments").getJSONObject(0).getStr("cid");
JSONObject replies = douyinService.getCommentReply(awemeId, commentId, 0L, 20, null);
```

现在你的项目拥有了完整的抖音数据获取能力！🎉