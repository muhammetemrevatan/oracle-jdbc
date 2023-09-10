-- SAVE_TUTORIAL
INSERT INTO tutorials (title, description, content, status, createdAt, updatedAt, createdBy, updatedBy) VALUES(?,?,?,?,?,?,?,?);

-- GET_TUTORIAL
SELECT * FROM tutorials WHERE id = ?;

-- GET_TUTORIALS
SELECT * FROM tutorials ORDER BY ID;

-- GET_TITLE_TUTORIAL
SELECT * FROM tutorials WHERE INSTR(title, ?) > 0 ORDER BY ID;

-- DELETE_TUTORIAL
DELETE FROM tutorials WHERE id = ?;

-- DELETE_TUTORIALS
TRUNCATE TABLE tutorials;

-- UPDATE_TUTORIAL
UPDATE tutorials SET TITLE=?, DESCRIPTION=?, CONTENT=?, STATUS=? WHERE id=?;