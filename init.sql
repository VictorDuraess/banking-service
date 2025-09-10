create table if not exists address(
    id serial primary key,
    street text not null,
    thoroughfare text not null,
    complement text not null,
    number int not null
);

create table if not exists branch(
    id serial primary key,
    name text not null,
    legal_name text not null,
    tax_id text not null,
    address_id int references address
);