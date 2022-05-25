create table bdsm.product
(
    id                            serial
        constraint product_pk
            primary key,
    name                          varchar          not null,
    uuid                          uuid             not null,
    water_g                       double precision not null,
    dry_matirial_g                double precision not null,
    proteins_g                    double precision not null,
    vegetable_protein_g           double precision not null,
    carbohydrates_all_g           double precision not null,
    sugar_g                       double precision not null,
    mineral_substances_mg         double precision not null,
    sodium_mg                     double precision not null,
    magnesium_mg                  double precision not null,
    phosphorus_mg                 double precision not null,
    potassium_mg                  double precision not null,
    calcium_mg                    double precision not null,
    iodine_mkrg                   double precision not null,
    vitamin_b2_mg                 double precision not null,
    niacin_vitamin_pp_mg          double precision not null,
    niacin_equivalent_mkrg        double precision not null,
    vitamin_b6_mg                 double precision not null,
    alcohol_g                     double precision not null,
    energy_kj                     double precision not null,
    energy_value_kcal             double precision not null,
    iron_mg                       double precision not null,
    vitamin_b1_mg                 double precision not null,
    zinc_mg                       double precision not null,
    fat_g                         double precision not null,
    saturated_fatty_acids_g       double precision not null,
    monounsaturated_fatty_acids_g double precision not null,
    polyunsaturated_fatty_acids_g double precision not null,
    starch_g                      double precision not null,
    fiber_materials_g             double precision not null,
    selenium_mkrg                 double precision not null,
    vitamin_a_retinol_mkrg        double precision not null,
    vitamin_e_tocopherol_mg       double precision not null,
    folic_acid_mkrg               double precision not null,
    vitamin_c_mg                  double precision not null,
    animal_protein_g              double precision not null,
    cholesterol_mg                double precision not null,
    vitamin_d_mkrg                double precision not null,
    vitamin_b12_mkrg              double precision not null
);

create unique index product_id_uindex
    on bdsm.product (id);


create unique index table_name_uuid_uindex
    on bdsm.product (uuid);


-- RECIPE TABLE
create table bdsm.recipe
(
    id          serial,
    uuid        uuid not null,
    name        varchar,
    recipe_text varchar
);

create unique index recipe_id_uindex
    on bdsm.recipe (id);

create unique index recipe_uuid_uindex
    on bdsm.recipe (uuid);

-- ProductAndQuantity table
create table bdsm.product_and_quantity
(
    id         serial
        constraint product_and_quantity_pk
            primary key,
    product_id int              not null,
    quantity   double precision not null
);

create unique index product_and_quantity_id_uindex
    on bdsm.product_and_quantity (id);

-- RECIPE AND PRODUCTS TABLE
create table bdsm.recipe_products_list
(
    recipe_id        int not null,
    products_list_id int not null
);
alter table bdsm.recipe_products_list
    add constraint recipe_product_product_id_fk
        foreign key (products_list_id) references bdsm.product_and_quantity (id);

alter table bdsm.recipe_products_list
    add constraint recipe_product_recipe_id_fk
        foreign key (recipe_id) references bdsm.recipe (id);


-- WAREHOUSE TABLE
create table bdsm.warehouse
(
    id           serial,
    uuid         uuid             not null,
    product_id   integer          not null,
    amount       double precision not null,
    invoice      varchar(255)     not null,
    product_type pg_enum
);

alter table bdsm.warehouse
    owner to postgres;

create unique index warehouse_id_uindex
    on bdsm.warehouse (id);

-- USERS TABLE
create table bdsm.users
(
    id       serial
        constraint users_pk
            primary key,
    client   varchar not null,
    password varchar not null
);
-- ROLE TABLE
create table bdsm.role
(
    id   serial
        constraint role_pk
            primary key,
    name  varchar not null
--             on update cascade on delete cascade,
);

-- USERS AND ROLES TABLE

create table bdsm.users_roles
(
    client_id integer not null,
    roles_id  integer not null
--             on update cascade on delete cascade,
);

alter table bdsm.users_roles
    add constraint users_id_roles_id_fk
        foreign key (client_id) references bdsm.users (id);

alter table bdsm.users_roles
    add constraint users_roles_users_id_fk
        foreign key (roles_id) references bdsm.role (id);


--  MENU_DAY TABLE
create table bdsm.menu_day
(
    id         serial
        constraint menu_day_pk
            primary key,
    day_number int not null
);

create unique index menu_day_day_number_uindex
    on bdsm.menu_day (day_number);


-- MENU_DAY_DAY_RECIPES TABLE

create table bdsm.menu_day_day_recipes
(
    menu_day_id    int not null
        constraint menu_day_day_recipes_menu_day_id_fk
            references bdsm.menu_day(id)
            on update cascade on delete cascade,
    day_recipes_id int not null
        constraint menu_day_day_recipes_recipe_id_fk
            references bdsm.recipe (id)
);


