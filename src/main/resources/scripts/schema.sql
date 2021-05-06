CREATE TABLE url (
    url_id bigint NOT NULL AUTO_INCREMENT,
    long_url longtext,
    short_url varchar(50),
    view_count int,
    PRIMARY KEY (`url_id`)
) ;