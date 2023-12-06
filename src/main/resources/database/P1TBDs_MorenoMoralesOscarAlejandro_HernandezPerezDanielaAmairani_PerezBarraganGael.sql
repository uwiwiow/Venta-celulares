create or replace table celular
(
    NumCelular int(9) auto_increment
        primary key,
    Marca      varchar(10)   null,
    NS         varchar(60)   null,
    Modelo     int(4)        null,
    Color      varchar(10)   null,
    Precio     float         null,
    Inventario int default 0 null,
    constraint NS
        unique (NS)
);

create or replace table cliente
(
    NumCliente int(5) auto_increment
        primary key,
    NombreC    varchar(20) null,
    ApePC      varchar(15) null,
    ApeMC      varchar(15) null,
    CalleC     varchar(25) null,
    Numero     int(4)      null,
    Colonia    varchar(55) null
);

create or replace table empleado
(
    NumEmpleado int(9) auto_increment
        primary key,
    NombreE     varchar(20)                       null,
    ApeP        varchar(20)                       null,
    ApeM        varchar(20)                       null,
    Turno       varchar(15) default 'No definido' null
        check (`Turno` in ('Matutino', 'Vespertino', 'Mixto'))
);

create or replace table venta
(
    FolioV int(9) not null
        primary key,
    NoE    int(9) not null,
    NoC    int(5) not null,
    Fecha  date   null,
    constraint venta_ibfk_1
        foreign key (NoE) references empleado (NumEmpleado)
            on delete cascade,
    constraint venta_ibfk_2
        foreign key (NoC) references cliente (NumCliente)
            on delete cascade
);

create or replace table dsv
(
    NoV      int(10) auto_increment
        primary key,
    FolioV   int(9) not null,
    NoC      int(9) null,
    Cantidad int(1) null,
    Importe  float  null,
    constraint dsv_ibfk_1
        foreign key (FolioV) references venta (FolioV)
            on delete cascade,
    constraint dsv_ibfk_2
        foreign key (NoC) references celular (NumCelular)
            on delete set null
);

create or replace definer = root@localhost view megavista as
select `v`.`FolioV`    AS `FolioV`,
       `v`.`Fecha`     AS `Fecha`,
       `c`.`NombreC`   AS `Cliente`,
       `e`.`NombreE`   AS `Empleado`,
       `dv`.`Cantidad` AS `Cantidad`,
       `dv`.`Importe`  AS `Importe`,
       `cl`.`Marca`    AS `marcacelular`,
       `cl`.`Modelo`   AS `modelo`,
       `cl`.`Color`    AS `color`,
       `cl`.`Precio`   AS `precio`
from ((((`vc`.`venta` `v` join `vc`.`dsv` `dv` on (`v`.`FolioV` = `dv`.`NoV`)) join `vc`.`cliente` `c`
        on (`v`.`NoC` = `c`.`NumCliente`)) join `vc`.`empleado` `e`
       on (`v`.`NoE` = `e`.`NumEmpleado`)) join `vc`.`celular` `cl` on (`dv`.`NoV` = `cl`.`NumCelular`));

create or replace definer = root@localhost view totalventasporempleado as
select `e`.`NumEmpleado` AS `NumEmpleado`, `e`.`NombreE` AS `NombreE`, sum(`dv`.`Importe`) AS `TotalVentas`
from ((`vc`.`empleado` `e` join `vc`.`venta` `v` on (`e`.`NumEmpleado` = `v`.`NoE`)) join `vc`.`dsv` `dv`
      on (`v`.`FolioV` = `dv`.`FolioV`))
group by `e`.`NumEmpleado`, `e`.`NombreE`
order by sum(`dv`.`Importe`) desc;

create or replace definer = root@localhost view vistaempleadospornombreyapelido as
select `vc`.`empleado`.`NumEmpleado` AS `NumEmpleado`,
       `vc`.`empleado`.`NombreE`     AS `NombreE`,
       `vc`.`empleado`.`ApeP`        AS `ApeP`,
       `vc`.`empleado`.`ApeM`        AS `ApeM`
from `vc`.`empleado`
where `vc`.`empleado`.`NombreE` like '%a'
   or `vc`.`empleado`.`ApeP` like '%ez'
   or `vc`.`empleado`.`ApeM` like '%Garcia%';

create or replace definer = root@localhost view vistatotalventasporcolor as
select `c`.`Color` AS `Color`, sum(`dv`.`Importe`) AS `TotalVentas`
from (`vc`.`celular` `c` join `vc`.`dsv` `dv` on (`c`.`NumCelular` = `dv`.`NoV`))
group by `c`.`Color`
order by `c`.`Color`;

create or replace definer = root@localhost view vistaventacelularesporprecio as
select `v`.`FolioV`    AS `FolioV`,
       `c`.`NombreC`   AS `NombreC`,
       `c`.`ApePC`     AS `ApePC`,
       `c`.`ApeMc`     AS `ApeMC`,
       `dv`.`Cantidad` AS `Cantidad`,
       `dv`.`Importe`  AS `importe`
from ((`vc`.`venta` `v` join `vc`.`dsv` `dv` on (`v`.`FolioV` = `dv`.`FolioV`)) join `vc`.`cliente` `c`
      on (`v`.`NoC` = `c`.`NumCliente`))
where `dv`.`Importe` between '12000' and '30000';

