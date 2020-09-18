ALTER TABLE elements_recorder_schema.article
ADD COLUMN updated_at timestamp default CURRENT_TIMESTAMP not null;

CREATE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_updated_at BEFORE UPDATE
ON elements_recorder_schema.article FOR EACH ROW EXECUTE PROCEDURE
update_updated_at_column();