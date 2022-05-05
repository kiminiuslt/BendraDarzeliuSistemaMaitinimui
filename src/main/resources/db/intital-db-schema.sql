create table bdsm.product
(
    id                            serial
        constraint product_pk
            primary key,
    name                          varchar not null,
    uuid                          uuid    not null,
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
    iodine_mkrg                     double precision not null,
    vitamin_b2_mg               double precision not null,
    niacin_vitamin_pp_mg      double precision not null,
    niacin_equivalent_mkrg        double precision not null,
    vitamin_b6_mg               double precision not null,
    alcohol_g                     double precision not null,
    energy_kj                   double precision not null,
    energy_value_kcal             double precision not null,
    iron_mg                       double precision not null,
    vitamin_b1_mg               double precision not null,
    zinc_mg                       double precision not null,
    fat_g                         double precision not null,
    saturated_fatty_acids_g       double precision not null,
    monounsaturated_fatty_acids_g double precision not null,
    polyunsaturated_fatty_acids_g double precision not null,
    starch_g                      double precision not null,
    fiber_materials_g             double precision not null,
    selenium_mkrg                   double precision not null,
    vitamin_a_retinol_mkrg      double precision not null,
    vitamin_e_tocopherol_mg   double precision not null,
    folic_acid_mkrg                 double precision not null,
    vitamin_c_mg                double precision not null,
    animal_protein_g              double precision not null,
    cholesterol_mg                double precision not null,
    vitamin_d_mkrg                double precision not null,
    vitamin_b12_mkrg              double precision not null
);

create unique index product_id_uindex
    on bdsm.product (id);


create unique index table_name_uuid_uindex
    on bdsm.product (uuid);