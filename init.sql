CREATE TABLE category
(
    id_category SERIAL       NOT NULL,
    name        VARCHAR(256) NOT NULL
);
ALTER TABLE category
    ADD CONSTRAINT pk_category PRIMARY KEY (id_category);

CREATE TABLE category_menu_item
(
    id_category  SERIAL NOT NULL,
    id_menu_item SERIAL NOT NULL
);
ALTER TABLE category_menu_item
    ADD CONSTRAINT pk_category_menu_item PRIMARY KEY (id_menu_item, id_category);

CREATE TABLE employee
(
    id_employee SERIAL       NOT NULL,
    name        VARCHAR(256) NOT NULL,
    surname     VARCHAR(256) NOT NULL,
    position    VARCHAR(256) NOT NULL,
    salary      INTEGER      NOT NULL,
    archived    BOOLEAN      NOT NULL
);
ALTER TABLE employee
    ADD CONSTRAINT pk_employee PRIMARY KEY (id_employee);

CREATE TABLE menu_item
(
    id_menu_item SERIAL       NOT NULL,
    name         VARCHAR(256) NOT NULL,
    price        VARCHAR(256) NOT NULL,
    archived     BOOLEAN      NOT NULL
);
ALTER TABLE menu_item
    ADD CONSTRAINT pk_menu_item PRIMARY KEY (id_menu_item);

CREATE TABLE "order"
(
    id_order             SERIAL    NOT NULL,
    employee_id_employee INTEGER   NOT NULL,
    date_time            TIMESTAMP NOT NULL
);
ALTER TABLE "order"
    ADD CONSTRAINT pk_order PRIMARY KEY (id_order);

CREATE TABLE order_detail
(
    id_order     SERIAL  NOT NULL,
    id_menu_item SERIAL  NOT NULL,
    quantity     INTEGER NOT NULL
);
ALTER TABLE order_detail
    ADD CONSTRAINT pk_order_detail PRIMARY KEY (id_order, id_menu_item);

ALTER TABLE category_menu_item
    ADD CONSTRAINT fk_category_menu_item_category FOREIGN KEY (id_category) REFERENCES category (id_category) ON DELETE CASCADE;
ALTER TABLE category_menu_item
    ADD CONSTRAINT fk_category_menu_item_menu_item FOREIGN KEY (id_menu_item) REFERENCES menu_item (id_menu_item) ON DELETE CASCADE;

ALTER TABLE "order"
    ADD CONSTRAINT fk_order_employee FOREIGN KEY (employee_id_employee) REFERENCES employee (id_employee) ON DELETE CASCADE;

ALTER TABLE order_detail
    ADD CONSTRAINT fk_order_detail_order FOREIGN KEY (id_order) REFERENCES "order" (id_order) ON DELETE CASCADE;
ALTER TABLE order_detail
    ADD CONSTRAINT fk_order_detail_menu_item FOREIGN KEY (id_menu_item) REFERENCES menu_item (id_menu_item) ON DELETE CASCADE;

INSERT INTO employee (name, surname, position, salary, archived)
VALUES ('Boris', 'Jiráček', 'barista', 25000, false);
INSERT INTO employee (name, surname, position, salary, archived)
VALUES ('Valentýn', 'Kovanda', 'manager', 30000, false);
INSERT INTO employee (name, surname, position, salary, archived)
VALUES ('Robert', 'Šmolík', 'barista', 20000, false);
INSERT INTO employee (name, surname, position, salary, archived)
VALUES ('Simona', 'Zárubová', 'barista', 22000, false);
INSERT INTO employee (name, surname, position, salary, archived)
VALUES ('Hana', 'Jiráčková', 'barista', 22000, false);

INSERT INTO menu_item (name, price, archived)
VALUES ('espresso', 50, false);
INSERT INTO menu_item (name, price, archived)
VALUES ('cappuccino', 65, false);
INSERT INTO menu_item (name, price, archived)
VALUES ('latte', 75, false);
INSERT INTO menu_item (name, price, archived)
VALUES ('water', 33, false);
INSERT INTO menu_item (name, price, archived)
VALUES ('cola', 38, false);
INSERT INTO menu_item (name, price, archived)
VALUES ('cheesecake', 85, false);
INSERT INTO menu_item (name, price, archived)
VALUES ('croissant', 50, false);
INSERT INTO menu_item (name, price, archived)
VALUES ('cupcake', 40, false);

INSERT INTO category (name)
VALUES ('drink');
INSERT INTO category (name)
VALUES ('food');

INSERT INTO category_menu_item (id_category, id_menu_item)
VALUES ((SELECT id_category FROM category WHERE name = 'drink'),
        (SELECT id_menu_item FROM menu_item WHERE name = 'espresso'));
INSERT INTO category_menu_item (id_category, id_menu_item)
VALUES ((SELECT id_category FROM category WHERE name = 'drink'),
        (SELECT id_menu_item FROM menu_item WHERE name = 'cappuccino'));
INSERT INTO category_menu_item (id_category, id_menu_item)
VALUES ((SELECT id_category FROM category WHERE name = 'drink'),
        (SELECT id_menu_item FROM menu_item WHERE name = 'latte'));
INSERT INTO category_menu_item (id_category, id_menu_item)
VALUES ((SELECT id_category FROM category WHERE name = 'drink'),
        (SELECT id_menu_item FROM menu_item WHERE name = 'water'));
INSERT INTO category_menu_item (id_category, id_menu_item)
VALUES ((SELECT id_category FROM category WHERE name = 'drink'),
        (SELECT id_menu_item FROM menu_item WHERE name = 'cola'));
INSERT INTO category_menu_item (id_category, id_menu_item)
VALUES ((SELECT id_category FROM category WHERE name = 'food'),
        (SELECT id_menu_item FROM menu_item WHERE name = 'cheesecake'));
INSERT INTO category_menu_item (id_category, id_menu_item)
VALUES ((SELECT id_category FROM category WHERE name = 'food'),
        (SELECT id_menu_item FROM menu_item WHERE name = 'croissant'));
INSERT INTO category_menu_item (id_category, id_menu_item)
VALUES ((SELECT id_category FROM category WHERE name = 'food'),
        (SELECT id_menu_item FROM menu_item WHERE name = 'cupcake'));

INSERT INTO "order" (employee_id_employee, date_time)
VALUES ((SELECT id_employee FROM employee WHERE name = 'Boris'),
        '2022-12-1'::timestamp without time zone);
INSERT INTO order_detail (id_order, id_menu_item, quantity)
VALUES (currval(pg_get_serial_sequence('order', 'id_order')),
        (SELECT id_menu_item FROM menu_item WHERE name = 'espresso'),
        1);

INSERT INTO "order" (employee_id_employee, date_time)
VALUES ((SELECT id_employee FROM employee WHERE name = 'Valentýn'),
        '2022-12-10'::timestamp without time zone);
INSERT INTO order_detail (id_order, id_menu_item, quantity)
VALUES (currval(pg_get_serial_sequence('order', 'id_order')),
        (SELECT id_menu_item FROM menu_item WHERE name = 'cappuccino'),
        1);

INSERT INTO "order" (employee_id_employee, date_time)
VALUES ((SELECT id_employee FROM employee WHERE name = 'Robert'),
        '2022-12-15'::timestamp without time zone);
INSERT INTO order_detail (id_order, id_menu_item, quantity)
VALUES (currval(pg_get_serial_sequence('order', 'id_order')),
        (SELECT id_menu_item FROM menu_item WHERE name = 'latte'),
        1);

INSERT INTO "order" (employee_id_employee, date_time)
VALUES ((SELECT id_employee FROM employee WHERE name = 'Simona'),
        '2022-12-20'::timestamp without time zone);
INSERT INTO order_detail (id_order, id_menu_item, quantity)
VALUES (currval(pg_get_serial_sequence('order', 'id_order')),
        (SELECT id_menu_item FROM menu_item WHERE name = 'water'),
        2);

INSERT INTO "order" (employee_id_employee, date_time)
VALUES ((SELECT id_employee FROM employee WHERE name = 'Hana'),
        '2022-12-30'::timestamp without time zone);
INSERT INTO order_detail (id_order, id_menu_item, quantity)
VALUES (currval(pg_get_serial_sequence('order', 'id_order')),
        (SELECT id_menu_item FROM menu_item WHERE name = 'cola'),
        1);

INSERT INTO "order" (employee_id_employee, date_time)
VALUES ((SELECT id_employee FROM employee WHERE name = 'Boris'),
        '2023-1-1'::timestamp without time zone);
INSERT INTO order_detail (id_order, id_menu_item, quantity)
VALUES (currval(pg_get_serial_sequence('order', 'id_order')),
        (SELECT id_menu_item FROM menu_item WHERE name = 'cheesecake'),
        1);

INSERT INTO "order" (employee_id_employee, date_time)
VALUES ((SELECT id_employee FROM employee WHERE name = 'Boris'),
        '2023-1-10'::timestamp without time zone);
INSERT INTO order_detail (id_order, id_menu_item, quantity)
VALUES (currval(pg_get_serial_sequence('order', 'id_order')),
        (SELECT id_menu_item FROM menu_item WHERE name = 'latte'),
        1);
INSERT INTO order_detail (id_order, id_menu_item, quantity)
VALUES (currval(pg_get_serial_sequence('order', 'id_order')),
        (SELECT id_menu_item FROM menu_item WHERE name = 'cupcake'),
        1);
