Use Microworld;

GRANT ALL PRIVILEGES ON Microworld.* TO micro_admin;

GRANT select ON Microworld.* TO micro_reader;

GRANT insert, delete, update ON Microworld.* to micro_writer;