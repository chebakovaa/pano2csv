select table_name from INFORMATION_SCHEMA.views WHERE table_schema = 'neo' union select table_name from INFORMATION_SCHEMA.tables WHERE table_schema = 'neo' AND table_type = 'BASE TABLE'