create table bdsm.product
(
    id    bigint      not null
        primary key,
    uuid  uuid        not null,
    name  varchar(20) not null,
    kcal  double precision,
    carbs double precision
);

alter table bdsm.product
    owner to postgres;