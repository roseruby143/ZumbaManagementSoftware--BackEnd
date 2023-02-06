create table participants(
	participanId int NOT NULL AUTO_INCREMENT,
	participantName varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	street varchar(255),
	city varchar(255),
	state varchar(255),
	country varchar(255),
	pincode int,
	phone varchar(10) NOT NULL,
	batchId int NOT NULL,
	Primary key (participanId),
	CONSTRAINT fk_batch FOREIGN KEY (batchId)  
	REFERENCES batch(batchId) 
);
show tables;

create table batch (
	batchId int NOT NULL AUTO_INCREMENT,
	batchName varchar(255) NOT NULL,
	batchHours varchar(255) NOT NULL,
	batchMaxParticipant int,
	noOfParticipant int,
	roomNo int,
	zumbaTeacher varchar(255) NOT NULL,
	Primary key (batchId)
);
show tables;