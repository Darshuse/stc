CREATE TABLE "user" (
    user_id int PRIMARY KEY,
    username varchar(50) NOT NULL
);

INSERT INTO "user" (user_id,username) values(1,'John Doe'),(2,'Jane Don'),(3,'Alice Jones'),(4,'Lisa Romero');

CREATE TABLE training_details(
    user_training_id int PRIMARY KEY,
    user_id int NOT NULL,
    training_id int NOT NULL,
    training_date date NOT NULL,
    CONSTRAINT FK_user FOREIGN KEY(user_id)
   REFERENCES "user"(user_id )
);

insert into training_details(user_training_id,user_id,training_id,training_date) 
values(1,1,1,'2015-08-02'),
(2,2,1,'2015-08-03'),
(3,3,2,'2015-08-02'),
(4,4,2,'2015-08-04'),
(5,2,2,'2015-08-03'),
(6,1,1,'2015-08-02'),
(7,3,2,'2015-08-04'),
(8,4,3,'2015-08-03'),
(9,1,4,'2015-08-03'),
(10,3,1,'2015-08-02'),
(11,4,2,'2015-08-04'),
(12,3,2,'2015-08-02'),
(13,1,1,'2015-08-02'),
(14,4,3,'2015-08-03');
-- =======================result =============================

select 
     u.user_id,u.username,
	 t.training_id,
	 t.training_date,
	 count(1) as "count"
from 
   Training_details t
inner join "user" u 

ON 
    t.user_id=u.user_id
	
group by
  u.user_id ,
	t.training_id,
	t.training_date
having count(1) > 1 
order by
	training_date desc;