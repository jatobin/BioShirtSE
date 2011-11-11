CREATE DATABASE slipdb; 
CREATE USER 'slipuser'@'localhost' identified by 'tempslipuser';
grant select, insert, update, delete on slipdb.* to 'slipuser'@'localhost';

CREATE TABLE `users` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
CREATE TABLE `users_devices` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `device_id` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
CREATE TABLE `devices` (
  `device_id` varchar(32) NOT NULL DEFAULT '',
  `date` varchar(32) NOT NULL DEFAULT '',
  `data` varchar(6) NOT NULL DEFAULT '',
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;