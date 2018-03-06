drop table if exists deliver;
drop table if exists collect_tag;
drop table if exists collect_enterprise;
drop table if exists review;
drop table if exists inform_interview;
drop table if exists manager;
drop table if exists recruit_info_tag;
drop table if exists recruit_info;
drop table if exists tag;
drop table if exists enterprise;
drop table if exists student_detail;
drop table if exists resume_skill;
drop table if exists resume_club;
drop table if exists resume_organization;
drop table if exists resume_works;
drop table if exists resume_education;
drop table if exists resume_head;
drop table if exists resume;
drop table if exists student;

create table student(
	student_id  				BIGINT(20) AUTO_INCREMENT,
	user_name  					VARCHAR(20),
	mailbox  					VARCHAR(30),
	password  					VARCHAR(200),
	primary key(student_id)				
);
create table resume(
	resume_id  					BIGINT(20) AUTO_INCREMENT,
	student_id  				BIGINT(20),
	primary key(resume_id),
	foreign key(student_id) references student(student_id)
);
create table resume_head(
	resume_id  					BIGINT(20),
	name  						VARCHAR(20),
	address  					VARCHAR(50),
	phone_num  					CHAR(11),
	mailbox  					VARCHAR(30),
	primary key(resume_id),
	foreign key(resume_id) references resume(resume_id)
);
create table resume_education(
	resume_education_id  		BIGINT(20) AUTO_INCREMENT,
	resume_id  					BIGINT(20),
	university_name    			VARCHAR(30),
	city  						VARCHAR(30),
	province  					VARCHAR(10),
	major  						VARCHAR(25),
	graduate_date   			DATE,
	grade  						int,
	honors   					VARCHAR(100),
	related_course  			VARCHAR(200),
	primary key(resume_education_id),
	foreign key(resume_id) references resume(resume_id) 
);
create table resume_works(
	resume_works_id  			BIGINT(20) AUTO_INCREMENT,
	resume_id  					BIGINT(20),
	company  					VARCHAR(30),
	city  						VARCHAR(30),
	province  					VARCHAR(10),
	job_name  					VARCHAR(20),
	project_name  				VARCHAR(30),
	start_date  				DATE,
	end_date  					DATE,
	sentence_1  				VARCHAR(100),
	sentence_2  				VARCHAR(100),
	sentence_3  				VARCHAR(100),
	sentence_4  				VARCHAR(100),
	primary key(resume_works_id),
	foreign key(resume_id) references resume(resume_id)  					
);
create table resume_organization(
	resume_organization_id  	BIGINT(20) AUTO_INCREMENT,
	resume_id  					BIGINT(20),
	name  						VARCHAR(30),
	job_name  					VARCHAR(20),
	start_date  				DATE,
	end_date  					DATE,
	sentence_1  				VARCHAR(100),
	sentence_2  				VARCHAR(100),
	sentence_3  				VARCHAR(100),
	primary key(resume_organization_id),
	foreign key(resume_id) references resume(resume_id) 
);
create table resume_club(
	resume_club_id  			BIGINT(20) AUTO_INCREMENT,
	resume_id  					BIGINT(20),
	name  						VARCHAR(30),
	job_name  					VARCHAR(20),
	start_date  				DATE,
	end_date  					DATE,
	sentence_1  				VARCHAR(100),
	sentence_2  				VARCHAR(100),
	primary key(resume_club_id),
	foreign key(resume_id) references resume(resume_id) 
);
create table resume_skill(
	resume_skill_id  			BIGINT(20) AUTO_INCREMENT,
	resume_id  					BIGINT(20),
	language  					VARCHAR(30),
	computer  					VARCHAR(30),
	hobby  						VARCHAR(100),
	primary key(resume_skill_id),
	foreign key(resume_id) references resume(resume_id) 
);
create table student_detail(
	student_id  				BIGINT(20),
	student_mailbox  			VARCHAR(30),
	phone_num  					CHAR(11),
	university_name  			VARCHAR(30),
	major  						VARCHAR(25),
	grade  						VARCHAR(10),
	intention_city  			VARCHAR(300),
	intention_industry  		VARCHAR(300),
	intention_function  		VARCHAR(300),
	validation  				BOOLEAN,
	primary key(student_id),
	foreign key(student_id) references student(student_id)
);
create table enterprise(
	enterprise_id  				BIGINT(20) AUTO_INCREMENT,
	user_name  					VARCHAR(20),
	name  						VARCHAR(30),
	mailbox  					VARCHAR(30),
	phone_num  					VARCHAR(30),
	password  					VARCHAR(200),
	enterprise_introduction  	VARCHAR(500),
	icon_address  				VARCHAR(150),
	primary key(enterprise_id)
);
create table tag(
	tag_id  					int AUTO_INCREMENT,
	name  						VARCHAR(20),
	primary key(tag_id)
);
create table recruit_info(
	recruit_info_id  			BIGINT(20) AUTO_INCREMENT,
	enterprise_id  				BIGINT(20),
	job_name  					VARCHAR(20),
	job_describe  				VARCHAR(500),
	job_require  				VARCHAR(500),
	location  					VARCHAR(50),
	low_salary  				int,
	high_salary   				int,
	date_time  					timestamp,
	deadline  					VARCHAR(30),
	have_delete  				BOOLEAN,
	primary key(recruit_info_id),
	foreign key(enterprise_id) references enterprise(enterprise_id)
);
create table recruit_info_tag(
	recruit_info_id  			BIGINT(20),
	tag_id  					int,
	primary key(recruit_info_id,tag_id),
	foreign key(recruit_info_id) references recruit_info(recruit_info_id),
	foreign key(tag_id) references tag(tag_id)
);
create table manager(
	manager_id  				int AUTO_INCREMENT,
	user_name  					VARCHAR(20),
	password  					VARCHAR(200),
	primary key(manager_id)
);

create table inform_interview(
	inform_interview_id  		BIGINT(20) AUTO_INCREMENT,
	enterprise_id  				BIGINT(20),
	student_id  				BIGINT(20),
	content  					VARCHAR(500),
	date_time  					timestamp,
	primary key(inform_interview_id),
	foreign key(enterprise_id) references enterprise(enterprise_id),
	foreign key(student_id) references student(student_id)
);
create table review(
	enterprise_id  				BIGINT(20),
	student_id  				BIGINT(20),
	job_name  					VARCHAR(20),
	content  					VARCHAR(500),
	date_time  					timestamp,
	primary key(enterprise_id, student_id),
	foreign key(enterprise_id) references enterprise(enterprise_id),
	foreign key(student_id) references student(student_id)
);
create table collect_enterprise(
	student_id  				BIGINT(20),
	enterprise_id  				BIGINT(20),
	primary key(enterprise_id, student_id),
	foreign key(student_id) references student(student_id),
	foreign key(enterprise_id) references enterprise(enterprise_id)
);
create table collect_tag(
	student_id  				BIGINT(20),
	tag_id  					int,
	primary key(tag_id, student_id),
	foreign key(student_id) references student(student_id),
	foreign key(tag_id) references tag(tag_id)
);
create table deliver(
	deliver_id  				BIGINT(20) AUTO_INCREMENT,
	resume_id  					BIGINT(20),
	enterprise_id  				BIGINT(20),
	recruit_info_id  			BIGINT(20),
	date_time  					timestamp,
	have_read  					BOOLEAN,
	have_delete  				BOOLEAN,
	primary key(deliver_id),
	foreign key(resume_id) references resume(resume_id),
	foreign key(enterprise_id) references enterprise(enterprise_id),
	foreign key(recruit_info_id) references recruit_info(recruit_info_id)
);

insert into manager
	values(0, 'admin', 'E228C3B57EA3A4DD9FBB68D9ACEDA997F8BD1C4E7230B1016E3341B39D2FE6EF3A1178F0482C8FE2F9F9C5B4B2395B19E56D6BA8617828E79FC1773FC5DF40DC30DBEDCA387C431929C646D69B2F9F7A');

insert into student
	values(1,'syunk','408271272@qq.com','3277E8DD42812340F56B2997A9C6E801F9AE2C5DAA954DED676C6F1B8297A879B75185943D90B146C07401A0EBF57543533C646C0AB40D9C3035454757B92FA5CF278EE04F8D634D5E984371425E9179');

insert into enterprise
	values(1,'baidu','百度','mbaidu@baidu.com','86-10-5992 8888','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'百度是一家持续创新的,以"用科技让复杂世界更简单"为使命的高科技公司','/public/assets/img/comlogo/baidu.jpg'),
	(2,'aiqiyi','爱奇艺','maiqiyi@aiqiyi.com','86-10-8000 6459','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'爱奇艺是一家为了视频事业而献身的伟大公司','/public/assets/img/comlogo/爱奇艺.png'),
	(3,'4399','4399','m4399@4399.com','86-10-4566 8885','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/4399.png'),
	(4,'ebay','易贝','mebay@ebay.com','86-10-5864 6946','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/ebay.png'),
	(5,'IBM','IBM','mibm@ibm.com','86-10-8889 6465','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/IBM.png'),
	(6,'TCL','TCL','mtcl@tcl.com','86-10-6494 4646','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/TCL.pgn'),
	(7,'alibaba','阿里巴巴','malibaba@alibaba.com','86-10-4564 7789','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/阿里巴巴.png'),
	(8,'baidumap','百度地图','mbaidumap@baidumap.com','86-10-9999 7746','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/百度地图.png'),
	(9,'baoma','宝马','mbaoma@baoma.com','86-10-9859 7646','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/宝马.png'),
	(10,'danone','达能','mdanone@danone.com','86-10-9799 7346','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/达能.png'),
	(11,'dazhong','大众','mdazhong@dazhong.com','86-10-4444 7254','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/大众.png'),
	(12,'didi','滴滴出行','mdidi@didi.com','86-10-9999 7746','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/滴滴出行.png'),
	(13,'Neusoft','东软','mneusoft@neusoft.com','86-10-7786 7566','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/东软.png'),
	(14,'hongbanbao','红板报','mhongbanbao@hongbanbao.com','86-10-9779 7556','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/红板报.png'),
	(15,'hunamweishi','湖南卫视','mhunamweishi@hunamweishi.com','86-10-9999 7746','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/湖南卫视.png'),
	(16,'ji','湖南卫视','mhunamweishi@hunamweishi.com','86-10-9999 7746','DC7BE88AB13D57CB571C0449D2DC75F9DFA8066B820A451139712FFF99880144A11901878A4CA6F50A319CF4704963920ED97D4B765A776D7010D637728056744061575798B6686695464C3DDB165CDB',
		'','/public/assets/img/comlogo/湖南卫视.png');

insert into student_detail
	values(1,'2015141463140@stu.scu.edu.cn','17761279281','四川大学','软件工程',2015,'[上海,成都]','[互联网]','[项目管理,技术]',0);

insert into tag
	values(1,'互联网');

insert into collect_enterprise
	values(1,1);

insert into collect_tag
	values(1,1);

insert into recruit_info
	values(1,1,'项目经理','指导项目进行','这是要求：我没写','南京','120000','240000','2017-06-12 17:52:08','2017-06-11',0),
			(2,1,'项目经理','指导项目进行','这是要求：我没写','成都','120000','240000','2017-06-13 17:52:08','2017-06-11',0),
			(3,1,'项目经理','指导项目进行','这是要求：我没写','成都','120000','240000','2017-06-14 17:52:08','2017-06-11',0),
			(4,1,'项目经理','指导项目进行','这是要求：我没写','成都','120000','240000','2017-06-15 17:52:08','2017-06-11',0),
			(5,1,'项目经理','指导项目进行','这是要求：我没写','成都','120000','240000','2017-06-16 17:52:08','2017-06-11',0),
			(6,1,'项目经理','指导项目进行','这是要求：我没写','成都','120000','240000','2017-06-17 17:52:08','2017-06-11',0),
			(7,1,'项目经理','指导项目进行','这是要求：我没写','成都','120000','240000','2017-06-18 17:52:08','2017-06-11',0),
			(8,1,'项目经理','指导项目进行','这是要求：我没写','成都','120000','240000','2017-06-19 17:52:08','2017-06-11',0),
			(9,1,'项目经理','指导项目进行','这是要求：我没写','成都','120000','240000','2017-06-10 17:52:08','2017-06-11',0),
			(10,1,'项目经理','指导项目进行','这是要求：我没写','成都','120000','240000','2017-06-09 17:52:08','2017-06-11',0),
			(11,1,'项目经理','指导项目进行','这是要求：我没写','成都','120000','240000','2017-06-10 17:52:08','2017-06-11',0),
			(12,1,'项目经理','指导项目进行','这是要求：我没写','成都','120000','240000','2017-06-22 17:52:08','2017-06-11',0);

insert into recruit_info_tag
	values(1,1),(2,1);

insert into resume
	values(1,1);

insert into deliver
	values(1,1,1,1,'2017-06-11 17:52:08',0,0);
