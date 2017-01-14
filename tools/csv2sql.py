import sys
import re

store_id = sys.argv[1]


def num_format(txt):
    return txt.replace(",", ".")


def sqlite_escape(txt):
    return txt.replace("'", "''")


def process_line(line):
    d = line.strip("\n").split("\t")
    sql = "INSERT INTO product (name, um, vat, mod_date, id_store, quantity, price, price_sell) " \
          "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');" \
          % (sqlite_escape(d[1]), d[2], d[3], d[6], store_id, num_format(d[4]), num_format(d[5]), 0)
    print(sql)


def main():
    pattern = re.compile("^[0-9]")
    for line in sys.stdin:
        if pattern.match(line):
            process_line(line)


if __name__ == '__main__':
    main()
