Use Microworld;

GRANT ALL PRIVILEGES ON MicroTope.* TO micro_admin;

GRANT select ON MicroTope.* TO micro_reader;

GRANT select,insert, delete, update ON MicroTope.* to micro_writer;