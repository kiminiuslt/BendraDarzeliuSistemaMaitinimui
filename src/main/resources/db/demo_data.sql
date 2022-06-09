INSERT INTO bdsm.menu_day (day_number)
VALUES (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7),
       (8),
       (9),
       (10),
       (11),
       (12),
       (13),
       (14),
       (15),
       (16),
       (17),
       (18),
       (19),
       (20);

INSERT INTO bdsm.product_and_quantity (product_id, quantity)
VALUES (349, 10),
       (47, 100),
       (276, 10),
       (297, 5),
       (387, 100),
       (507, 5),
       (294, 5),
       (509, 5),
       (458, 80),
       (509, 5),
       (43, 80),
       (460, 5),
       (39, 200),
       (507, 5),
       (297, 10),
       (15, 100),
       (400, 250),
       (85, 300);

INSERT INTO bdsm.recipe (uuid, name, recipe_text)
VALUES ('3eb64b7a-4c75-4c6b-b5a8-5121be4c390e', 'Špinatų salotos su braškėmis, feta ir balzaminiu padažu',
        '1.Špinatų lapelius švariai nuplauname ir gerai nusausiname (aš išsuku salotų džiovyklėje). Paskirstome į dvi didesnes lėkštes. 2.	Braškes supjaustome kaip norisi - ketvirčiais, riekelėmis ar pagal kitokią nuotaiką. Braškes dedame ant špinatų. 3.	Ant braškių užtrupiname (postambiais gabalėliais) fetos sūrį. Užberiame pasmulkintus graikinius riešutus ir keletą mėtų lapelių. 4.	Užpilo ingredientus dedame į nedidelį stiklainiuką, užsukame dangteliu ir stipriai papurtome bent pusę minutės, jog viskas susijungtų į puikų balzaminį padažą. 5.Padažu apšlakstome salotas. Jas apibarstome trupučiu aguonų sėklų ir pabarstome šviežiai grūstais pipirais. 6. Viskas! Tiekiame ir skanaujame!'),
       ('819e0bbd-257f-4e56-9d9e-8c34211face2', 'Salotos su ridikėliais, agurkais ir gaiviu užpilu',
        '1.Salotas nuplauti ir gerai nusausinti. 2.Agurką perpjauti išilgai pusiau ir riekelėmis. Ridikėlius nuvalyti ir supjaustyti griežinėliais. Krapus sukapoti. 3.Salotas suplėšyti ir išmaišyti su agurkais, ridikėliais ir krapais. 4.Aliejų sumaišyti su citrinos sultimis, druska ir pipirais. Aplieti salotas ir išmaišyti. Skanaus!'),
       ('c85de06e-b630-4eea-9128-124ee8feca5f', 'Bulvių sriuba su kruopomis', 'Perkama paruošta'),
       ('089d4cda-86af-4118-93e9-bf0856634880', 'Bulviniai blynai', 'Perkami jau paruošti. Pašildyti');


INSERT INTO bdsm.menu_day_day_recipes (menu_day_id, day_recipes_id)
VALUES (13, 1),
       (4, 3),
       (1, 3),
       (1, 2),
       (1, 1);

INSERT INTO bdsm.recipe_products_list (recipe_id, products_list_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (2, 10),
       (2, 11),
       (2, 12),
       (2, 13),
       (2, 14),
       (2, 15),
       (2, 16),
       (3, 17),
       (4, 18);

INSERT INTO bdsm.role (id, name)
VALUES (1, 'DIETIST'),
       (2, 'DIRECTOR'),
       (3, 'CULINARY'),
       (4, 'MANAGER'),
       (5, 'DEMO');

INSERT INTO bdsm.users (client, password)
VALUES ('admin', '{bcrypt}$2a$10$mV4G2Scqh3GqJr1ftK6/NeZIbyLfz7h5O5./vHvOpzK7QPjHw1V8K'),
       ('irena', '{bcrypt}$2a$10$bpfncfp6pV9lIGH/0PA5aehLSho8Kz2kmVFdPAE.f2Bx1/1X8FqDm');

INSERT INTO bdsm.users_roles (client_id, roles_id)
VALUES (1, 1),
       (2, 3);

INSERT INTO bdsm.warehouse (uuid, product_id, amount, invoice)
VALUES ('0460975c-a41a-4633-84ca-f7431e835bd9', 334, 10, 'RA-88799874487'),
       ('ff9db326-b213-4bb3-8d65-90d204a4808d', 78, 3.8, 'GL-778669-741841'),
       ('92b75563-bd9e-4f1f-8fdf-fa8eeb1fbf83', 30, 18, 'UL-888-174478T');
