CREATE TABLE IF NOT EXISTS app_user
(
    id serial NOT NULL,
    email character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    password character varying(255),
    role character varying(255),
    CONSTRAINT _user_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS token
(
    expired boolean NOT NULL,
    id serial NOT NULL,
    revoked boolean NOT NULL,
    user_id integer,
    content character varying(255) COLLATE pg_catalog."default",
    token_type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT token_pkey PRIMARY KEY (id),
    CONSTRAINT token_token_key UNIQUE (content),
    CONSTRAINT fkiblu4cjwvyntq3ugo31klp1c6 FOREIGN KEY (user_id)
        REFERENCES app_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);