-- lwl.comment definition

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `commentauthor` int(11) NOT NULL,
  `gmtcreate` bigint(20) DEFAULT NULL,
  `gmtmodify` bigint(20) DEFAULT NULL,
  `likecount` int(11) DEFAULT '0',
  `content` varchar(1024) DEFAULT NULL,
  `commentcount` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20220315 DEFAULT CHARSET=utf8 COMMENT='回复的对象id';

-- lwl.notification definition

CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notifier` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `outerid` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `gmtcreate` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- lwl.question definition

CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` text,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modify` bigint(20) DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `comment_count` int(11) DEFAULT '0',
  `view_count` int(11) DEFAULT '0',
  `like_count` int(11) DEFAULT '0',
  `tag` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- lwl.`user` definition

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `token` varchar(36) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modify` bigint(20) DEFAULT NULL,
  `bio` varchar(256) DEFAULT NULL,
  `avator_url` varchar(256) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;