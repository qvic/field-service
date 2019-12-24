CREATE OR REPLACE FUNCTION updateFieldIdByCoordinated() RETURNS TRIGGER AS
$BODY$
BEGIN
    NEW.field_id = f.id
                   FROM field AS f
                   WHERE st_contains(st_makepoint(NEW.latitude, NEW.longitude), f.gis_polygon);

    RETURN NEW:
END;
$BODY$ LANGUAGE plpgsql;


CREATE TRIGGER updateFieldId
    BEFORE INSERT
    ON measurement
    FOR EACH ROW
EXECUTE PROCEDURE updateFieldIdByCoordinated();