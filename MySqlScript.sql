create database sanasuma;

use sanasuma;

create table userDetails(
	autoId int,
	nameFull varchar(500),
	address	 varchar(1000),
	PRIMARY KEY (autoId)	
)DEFAULT CHARACTER SET=utf8;

create table userId(
	userName varchar(50),
	pasWrd	 varchar(1000),
	autoId int,
	PRIMARY KEY (userName),
	FOREIGN KEY (autoId) REFERENCES userDetails(autoId)
)DEFAULT CHARACTER SET=utf8;

create table topicType(
	topicType varchar(5000),
	topicTypeId int,
	PRIMARY KEY (topicTypeId)
)DEFAULT CHARACTER SET=utf8;

create table topics(
	topicname varchar(5000),
	topicType int,
	topicId int,
	PRIMARY KEY (topicId),
	FOREIGN KEY (topicType) REFERENCES topicType(topicTypeId)
)DEFAULT CHARACTER SET=utf8;

create table subtopic(
	subtopicId int,
	subtopicname varchar(5000),
	topicId int,
	PRIMARY KEY (subtopicId),
	FOREIGN KEY (topicId) REFERENCES topics(topicId)
)DEFAULT CHARACTER SET=utf8;

create table examTypes(
  	examTypeId int,
	examType varchar(1000),
	PRIMARY KEY (examTypeId)
)DEFAULT CHARACTER SET=utf8;

create table exam(
	autoId int,
	examName varchar(1000),
	examTypeId int,
	subtopicId int,
	PRIMARY KEY (autoId),
	FOREIGN KEY (examTypeId) REFERENCES examTypes(examTypeId),
	FOREIGN KEY (subtopicId) REFERENCES subtopic(subtopicId)
	
)DEFAULT CHARACTER SET=utf8;

create table answerType(
	questionAnswerType int,
	questionAnswerTypeName varchar(1000),
	PRIMARY KEY (questionAnswerType)
)DEFAULT CHARACTER SET=utf8;


create table answers(
	answerId int,
	ansewer varchar(5000),
	incorrectAnswer varchar(5000),
	answerType int,
	PRIMARY KEY (answerId),	
	FOREIGN KEY (answerType) REFERENCES answerType(questionAnswerType)
)DEFAULT CHARACTER SET=utf8;

create table questions(
	questionId int,
	autoId int,
	question varchar(5000),
	correctAnsewer int,
	PRIMARY KEY (questionId),
	FOREIGN KEY (autoId) REFERENCES exam(autoId),
	FOREIGN KEY (correctAnsewer) REFERENCES answers(answerId)
)DEFAULT CHARACTER SET=utf8;



create table UserExams(
	autoId int,
	userId int,
	examId int,
	PRIMARY KEY (autoId),
	FOREIGN KEY (userId) REFERENCES userDetails(autoId),
	FOREIGN KEY (examId) REFERENCES exam(autoId)
)DEFAULT CHARACTER SET=utf8;