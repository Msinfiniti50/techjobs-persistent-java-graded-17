--Part 1
/*
 Columns in 'jobs' table:
-id: INT (Not Null)
=employer: VARCHAR(255)(Null)
=name: VARCHAR(255)(Null)
=skill: VARCHAR(255 (Null)
*/
--Part 2
SELECT name FROM employer WHERE location = "St. Louis City";
--Part 3
DROP TABLE job;
--Part 4
SELECT *
FROM skill
JOIN job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_id IS NOT NULL
ORDER BY name ASC;