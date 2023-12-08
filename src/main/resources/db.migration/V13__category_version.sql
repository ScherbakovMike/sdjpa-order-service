alter table customer
    add column version integer;

alter table category
    add column version integer;

alter table product
    add column version integer;

alter table order_approval
    add column version integer;

alter table order_header
    add column version integer;

alter table order_line
    add column version integer;
