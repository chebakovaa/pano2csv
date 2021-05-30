select table_name from INFORMATION_SCHEMA.tables
WHERE table_schema = 'neo' and not table_name like '\_%'
