# Intro
문의 계시판 예제


### API
- 전체 조회 GET /api/post/all
- 게시판 생성 POST /api/board
- 해당 글 조회 POST /api/post/view
- 글 작성 POST /api/post
- 글 삭제 POST /api/post/delete
- 답글 작성 PSOT /api/reply

### init SQL
```sql
CREATE DATABASE `simple_board`

CREATE TABLE `board` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `board_name` varchar(100) NOT NULL,
    `status` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
)

CREATE TABLE `post` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `board_id` bigint NOT NULL,
    `user_name` varchar(50) NOT NULL,
    `password` varchar(4) NOT NULL,
    `email` varchar(50) NOT NULL,
    `status` varchar(50) NOT NULL,
    `title` varchar(100) NOT NULL,
    `content` text NULL,
    `posted_at` datetime NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT='게시글';

CREATE TABLE `reply` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `post_id` bigint NOT NULL,
    `user_name` varchar(50) NOT NULL,
    `password` varchar(4) NOT NULL,
    `status` varchar(50) NOT NULL,
    `title` varchar(50) NOT NULL,
    `content` text NOT NULL,
    `replied_at` datetime NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT='답글';
```

