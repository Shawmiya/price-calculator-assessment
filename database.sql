mysql -u root -p

use pricecal;

insert into products (product_name, units_per_carton, price_per_carton, adding_percentage, required_cartons, discount_percentage) values ('Penguin-ears', 20, 175, 0.3, 3, 0.1);

insert into products (product_name, units_per_carton, price_per_carton, adding_percentage, required_cartons, discount_percentage) values ('Horseshoe', 5, 825, 0.3, 3, 0.1);

MariaDB [pricecal]> select * from products;
+----+-------------------+---------------------+------------------+--------------+------------------+------------------+
| id | adding_percentage | discount_percentage | price_per_carton | product_name | required_cartons | units_per_carton |
+----+-------------------+---------------------+------------------+--------------+------------------+------------------+
|  1 |               0.3 |                 0.1 |              175 | Penguin-ears |                3 |               20 |
|  2 |               0.3 |                 0.1 |              825 | Horseshoe    |                3 |                5 |
+----+-------------------+---------------------+------------------+--------------+------------------+------------------+
2 rows in set (0.000 sec)




