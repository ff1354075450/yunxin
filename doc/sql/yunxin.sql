
CREATE TABLE user(
  account BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '用户id',
  password VARCHAR(20) NOT NULL COMMENT '用户密码',
  nick VARCHAR(20) NOT NULL COMMENT '用户昵称',
  icon VARCHAR(100) COMMENT '用户头像',
  token VARCHAR(100) COMMENT '用户登录令牌',
  gtm_create DATETIME DEFAULT now() ,
  gtm_modified DATETIME
)ENGINE=InnoDB DEFAULT CHARSET=utf8;