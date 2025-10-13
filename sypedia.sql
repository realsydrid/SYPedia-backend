DROP DATABASE IF EXISTS sypedia;

CREATE SCHEMA sypedia DEFAULT CHARACTER SET utf8mb4;

use sypedia;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

CREATE TABLE users (
                       user_no        BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id        VARCHAR(30)  NOT NULL UNIQUE,
                       email          VARCHAR(255) UNIQUE,
                       password       VARCHAR(255) NOT NULL,
                       oauth_provider VARCHAR(20),
                       oauth_subject  VARCHAR(100),
                       user_name      VARCHAR(50),
                       profile_image  VARCHAR(255),
                       created_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       UNIQUE KEY uq_oauth_provider_subject (oauth_provider, oauth_subject)
);

CREATE TABLE movies (
                        movie_no       BIGINT AUTO_INCREMENT PRIMARY KEY,
                        tmdb_id        BIGINT       NOT NULL UNIQUE,
                        title          VARCHAR(255) NOT NULL,
                        year           SMALLINT,
                        country        CHAR(2),
                        certification  VARCHAR(20),
                        poster_path    VARCHAR(255),
                        preview        TEXT,
                        rating_sum     BIGINT       NOT NULL DEFAULT 0,
                        rating_count   INT          NOT NULL DEFAULT 0,
                        comment_count  INT          NOT NULL DEFAULT 0,
                        created_at     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ratings (
                         rating_no  BIGINT AUTO_INCREMENT PRIMARY KEY,
                         movie_no   BIGINT   NOT NULL,
                         user_no    BIGINT   NOT NULL,
                         rating     TINYINT UNSIGNED NOT NULL,
                         created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         UNIQUE KEY uq_rating_user_movie (movie_no, user_no),
                         CONSTRAINT fk_ratings_movie FOREIGN KEY (movie_no) REFERENCES movies(movie_no) ON DELETE CASCADE,
                         CONSTRAINT fk_ratings_user  FOREIGN KEY (user_no)   REFERENCES users(user_no)  ON DELETE CASCADE
);

CREATE TABLE comments (
                          comment_no BIGINT AUTO_INCREMENT PRIMARY KEY,
                          movie_no   BIGINT   NOT NULL,
                          user_no    BIGINT   NOT NULL,
                          comment    TEXT     NOT NULL,
                          created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT fk_comments_movie FOREIGN KEY (movie_no) REFERENCES movies(movie_no) ON DELETE CASCADE,
                          CONSTRAINT fk_comments_user  FOREIGN KEY (user_no)   REFERENCES users(user_no)  ON DELETE CASCADE
);