-- SAVE_TUTORIAL
INSERT INTO tutorials (title, description, content, status, createdAt, updatedAt, createdBy, updatedBy) VALUES(?,?,?,?,?,?,?,?);

-- UPDATE_TUTORIAL
UPDATE tutorials SET title = ?, description = ?, content = ?, status = ?, updatedAt = ?, updatedBy = ? WHERE id = ?;

-- GET_TUTORIAL
SELECT * FROM tutorials WHERE id = ?;