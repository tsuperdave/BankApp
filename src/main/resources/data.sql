INSERT INTO users (username, password, enabled)
    values('user',
    'user',
    true);

INSERT INTO users (username, password, enabled)
    values('admin',
    'admin',
    true);

INSERT INTO authorities (username, authority)
    values('user', 'AccountHolder');

INSERT INTO authorities (username, authority)
    values('admin', 'admin');
