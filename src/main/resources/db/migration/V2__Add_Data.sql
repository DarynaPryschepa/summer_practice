insert into working_place (wp_id, created_at, created_by, updated_at, updated_by, name, city)
values (1, now(), 'Daryna', now(), 'Daryna', 'Working_place_211', 'Chernihiv');
insert into monitor (mon_id, created_at, created_by, updated_at, updated_by, length, height,
                     width, vesa, display_size, w_place_id)
values (1, now(), 'Samsung', now(), 'Jack',
        600, 800, 100, '600 x 800', 456, 1);

insert into pc (pc_id, created_at, created_by, updated_at, updated_by, length, height,
                width, hddsize, cpucount, w_place_id)
values (1, now(), 'Samsung', now(), 'Jack',
        600, 800, 100, 700, 4, 1);