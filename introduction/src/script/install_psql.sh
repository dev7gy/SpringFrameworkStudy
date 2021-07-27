# cat /etc/issue
# Ubuntu 18.04.4 LTS

sudo apt update
sudo apt install postgresql
sudo service postgresql status
# 10/main (port 5432): down
sudo service postgresql start
# Starting PostgreSQL 10 database server  [ OK ]

sudo -u postgres psql
create user test0901 password '비밀번호 부분' CREATEDB;
select * from pg_user;
# postgres=# \du
#                                   List of roles
# Role name |                         Attributes                         | Member of
#-----------+------------------------------------------------------------+-----------
# postgres  | Superuser, Create role, Create DB, Replication, Bypass RLS | {}
# test0901  | Create DB
# postgres=# create database introduction owner test0901;

# os 계정과 postgresql 계정을 맵핑시켜서 사용하지 않으려면 host를 주고 접근
sudo -u postgres psql -h 127.0.0.1 -U test0901 -d introduction -W