insert into users (username, password, enabled) values ('user1', '{noop}user1', true);
insert into users (username, password, enabled) values ('user2', '{noop}user2', true);
insert into users (username, password, enabled) values ('user3', '{noop}user3', true);

insert into authorities (username, authority) values('user1', 'USER');
insert into authorities (username, authority) values('user2', 'USER');
insert into authorities (username, authority) values('user3', 'USER');