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

INSERT INTO employee
VALUES (1, 'Boris', 'Jiráček', 'barista', 25000, false);
INSERT INTO employee
VALUES (2, 'Valentýn', 'Kovanda', 'manager', 30000, false);
INSERT INTO employee
VALUES (3, 'Robert', 'Šmolík', 'barista', 20000, false);
INSERT INTO employee
VALUES (4, 'Simona', 'Zárubová', 'barista', 22000, false);
INSERT INTO employee
VALUES (5, 'Hana', 'Jiráčková', 'barista', 22000, false);

INSERT INTO menu_item
VALUES (6, 'espresso', 50, false);
INSERT INTO menu_item
VALUES (7, 'cappuccino', 65, false);
INSERT INTO menu_item
VALUES (8, 'latte', 75, false);
INSERT INTO menu_item
VALUES (9, 'water', 33, false);
INSERT INTO menu_item
VALUES (10, 'cola', 38, false);
INSERT INTO menu_item
VALUES (11, 'cheesecake', 85, false);
INSERT INTO menu_item
VALUES (12, 'croissant', 50, false);
INSERT INTO menu_item
VALUES (13, 'cupcake', 40, false);

INSERT INTO category
VALUES (14, 'drink');
INSERT INTO category
VALUES (15, 'food');

INSERT INTO category_menu_item
VALUES (14, 6);
INSERT INTO category_menu_item
VALUES (14, 7);
INSERT INTO category_menu_item
VALUES (14, 8);
INSERT INTO category_menu_item
VALUES (14, 9);
INSERT INTO category_menu_item
VALUES (14, 10);
INSERT INTO category_menu_item
VALUES (15, 11);
INSERT INTO category_menu_item
VALUES (15, 12);
INSERT INTO category_menu_item
VALUES (15, 13);

INSERT INTO "order"
VALUES (16, 1, '2022-12-1'::timestamp without time zone);
INSERT INTO order_detail
VALUES (16, 6, 1);

INSERT INTO "order"
VALUES (17, 1, '2022-12-10'::timestamp without time zone);
INSERT INTO order_detail
VALUES (17, 7, 1);

INSERT INTO "order"
VALUES (18, 2, '2022-12-15'::timestamp without time zone);
INSERT INTO order_detail
VALUES (18, 8, 1);

INSERT INTO "order"
VALUES (19, 3, '2022-12-20'::timestamp without time zone);
INSERT INTO order_detail
VALUES (19, 10, 2);

INSERT INTO "order"
VALUES (20, 4, '2022-12-30'::timestamp without time zone);
INSERT INTO order_detail
VALUES (20, 12, 1);

INSERT INTO "order"
VALUES (21, 5, '2023-1-1'::timestamp without time zone);
INSERT INTO order_detail
VALUES (21, 7, 1);

INSERT INTO "order"
VALUES (22, 5, '2023-1-10'::timestamp without time zone);
INSERT INTO order_detail
VALUES (22, 6, 1);
INSERT INTO order_detail
VALUES (22, 11, 1);
