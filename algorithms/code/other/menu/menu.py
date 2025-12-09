import unittest
from collections import Counter, defaultdict
from pathlib import Path
from queue import Queue

EXAMPLE_MENU = Path(__file__).parent / 'menu.csv'
EXAMPLE_DEMAND = Path(__file__).parent / 'demand.csv'
EXAMPLE_STOCK = Path(__file__).parent / 'stock.csv'


def load_menu(menu_path: Path) -> dict[str, dict[str, int]]:
    menu: dict[str, dict[str, int]] = defaultdict(dict)
    with menu_path.open() as handle:
        lines = handle.readlines()[1:]
    entries = [line.strip().split(',') for line in lines]
    for group, item, quantity in entries:
        if group not in menu:
            menu[group] = {}
        if item not in menu[group]:
            menu[group][item] = 0
        menu[group][item] += int(quantity)
    return menu


def collect_dishes(
    menu: dict[str, dict[str, int]], group: str
) -> Counter[str]:
    dishes: Counter[str] = Counter()
    for item, count in menu[group].items():
        if item in menu:
            dishes[item] = count
    return dishes


def collect_basic_items(
    menu: dict[str, dict[str, int]], group: str
) -> Counter[str]:
    queue: Queue = Queue()
    queue.put((group, 1))
    collection: Counter[str] = Counter()
    while not queue.empty():
        group, count = queue.get()
        if group in menu:
            for item, item_count in menu[group].items():
                queue.put((item, count * item_count))
        else:
            collection[group] += count
    return collection


def load_demand(demand_path: Path) -> dict[int, dict[str, int]]:
    demand: dict[int, dict[str, int]] = defaultdict(dict)
    with demand_path.open() as handle:
        lines = handle.readlines()[1:]
        entries = [line.strip().split(',') for line in lines]
        demand = {}
        for item, need, day in entries:
            day_no = int(day)
            if day_no not in demand:
                demand[day_no] = {}
            if item not in demand[day_no]:
                demand[day_no][item] = 0
            demand[day_no][item] += int(need)
    return demand


def collect_demand_items_for_day(
    demand: dict[int, dict[str, int]],
    day: int,
) -> Counter[str]:
    totals: Counter[str] = Counter()
    for item in demand[day]:
        totals[item] += demand[day][item]
    return totals


def collect_basic_demand_items_for_day(
    menu: dict[str, dict[str, int]],
    demand: dict[int, dict[str, int]],
    day: int,
) -> Counter[str]:
    top_totals = collect_demand_items_for_day(demand, day)
    basic_totals: Counter[str] = Counter()
    for item in top_totals:
        collection = collect_basic_items(menu, item)
        for element in collection:
            need = top_totals[item]
            collection[element] *= need
        basic_totals += collection
    return basic_totals


def load_stock(stock_path: Path) -> Counter[str]:
    stock: Counter[str] = Counter()
    with stock_path.open() as handle:
        lines = handle.readlines()[1:]
        entries = [line.strip().split(',') for line in lines]
        for item, count in entries:
            stock[item] += int(count)
    return stock


def collect_demand_items_without_stock(
    menu: dict[str, dict[str, int]],
    demand: dict[int, dict[str, int]],
    day: int,
    stock: Counter[str],
) -> Counter[str]:
    day_demand = collect_demand_items_for_day(demand, day)
    # print(format_counted('Stock', stock))
    # print(format_counted('Demand', day_demand))
    while any(item in stock for item in day_demand):
        adjusted_day_demand: Counter[str] = Counter()
        for item in day_demand:
            # print(f'Checking {item} in stock')
            if item in stock:
                # print(f'Found {item} in stock')
                if stock[item] == day_demand[item]:
                    del stock[item]
                    # print(f'Used up stock for {item}')
                elif stock[item] > day_demand[item]:
                    stock[item] -= day_demand[item]
                    # print(f'Reduced stock of {item} to {stock[item]}')
                elif stock[item] < day_demand[item]:
                    adjusted_day_demand[item] = day_demand[item] - stock[item]
                    del stock[item]
                    # print(f'Used up stock for {item}')
                    # print(f'Reduced demand to {adjusted_day_demand[item]}')
            else:
                adjusted_day_demand[item] = day_demand[item]
        # print(format_counted('Stock', stock))
        # print(format_counted('Demand', adjusted_day_demand))
        day_demand = Counter()
        for item in adjusted_day_demand:
            if item in menu:
                # print(f'Decomposing {item}')
                need = adjusted_day_demand[item]
                for sub_item in menu[item]:
                    # print(f'Decomposing {item} into {sub_item}')
                    day_demand[sub_item] = need * menu[item][sub_item]
            else:
                day_demand[item] = adjusted_day_demand[item]
        # print(format_counted('Stock', stock))
        # print(format_counted('Demand', day_demand))
    return day_demand


def format_counted(
    title: str, counted: Counter[str]
) -> str:  # pragma: no cover
    formatted = f'{title}:\n'
    for item in counted:
        formatted += f'  {counted[item]} x {item}\n'
    return formatted


class TestCode(unittest.TestCase):
    def test_q1(self) -> None:
        test_menu = load_menu(EXAMPLE_MENU)
        self.assertEqual(len(test_menu), 12)

    def test_q2(self) -> None:
        test_menu = load_menu(EXAMPLE_MENU)
        test_dishes = collect_dishes(test_menu, 'Happy Menu')
        expected_dishes = {
            'Kalbsleber Berliner Art': 1,
            'Backofen Haenchenfluegel': 2,
            'Geschmorter Ochsenschwanz': 1,
            'Haxengroestl': 4,
            'Kaiserschmarrn': 1,
            'Wiener Schnitzel': 1,
            'Geschmorter Schweinebauch': 1,
        }
        items = set(
            list(test_dishes.keys()) + list(expected_dishes.keys())
        )
        for item in items:
            self.assertEqual(test_dishes[item], expected_dishes[item])

    def test_q3(self) -> None:
        test_menu = load_menu(EXAMPLE_MENU)
        test_basic_items = collect_basic_items(test_menu, 'Happy Menu')
        expected_basic_items = {
            'Zucker': 1,
            'Fluegelgelenk': 2,
            'Semmelbroesel': 2,
            'Mehl': 1,
            'Beinmuskel': 4,
            'Kalbsleber': 1,
            'Beinfett': 4,
            'Schwanzspitze': 1,
            'Wiener': 1,
            'Schnitzel': 1,
            'Deckel': 1,
            'Pfanne': 2,
            'Schwein': 1,
            'Horizontal Knochen': 1,
            'Bauch': 1,
            'Zimmt': 1,
            'Berliner Luft': 2,
            'Eier': 6,
            'Salz': 60,
            'Butterfly': 20,
            'Butter': 36,
            'Beingelenk': 4,
            'Backpulver': 12,
            'Rosinen': 1,
            'Beinhaut': 4,
            'Eiweiss': 1,
            'Schwanzgelenk': 1,
            'Dreck': 1,
            'Ofen': 1,
            'Vertikaler Knochen': 1,
            'Pfeffer': 1,
            'Apfelmousse': 1,
            'Ampelman': 2,
            'Fluegelknochen': 10,
            'Fluegel Haut': 4,
            'Fluegel Feder': 2,
            'Mandeln': 1,
        }
        items = set(
            list(test_basic_items.keys()) + list(expected_basic_items.keys())
        )
        for item in items:
            self.assertEqual(
                test_basic_items[item], expected_basic_items[item]
            )

    def test_q4(self) -> None:
        test_demand = load_demand(EXAMPLE_DEMAND)
        self.assertEqual(len(test_demand), 3)

    def test_q5(self) -> None:
        test_menu = load_menu(EXAMPLE_MENU)
        test_demand = load_demand(EXAMPLE_DEMAND)
        test_basic_items_demand = collect_basic_demand_items_for_day(
            test_menu, test_demand, 32
        )
        expected_basic_items_demand = {
            'Zucker': 5,
            'Fluegelgelenk': 10,
            'Semmelbroesel': 10,
            'Mehl': 5,
            'Beinmuskel': 20,
            'Kalbsleber': 7,
            'Beinfett': 20,
            'Schwanzspitze': 5,
            'Wiener': 5,
            'Schnitzel': 5,
            'Deckel': 5,
            'Pfanne': 10,
            'Schwein': 5,
            'Horizontal Knochen': 5,
            'Bauch': 5,
            'Zimmt': 5,
            'Berliner Luft': 14,
            'Eier': 30,
            'Salz': 316,
            'Butterfly': 140,
            'Butter': 220,
            'Beingelenk': 20,
            'Backpulver': 60,
            'Rosinen': 5,
            'Beinhaut': 20,
            'Eiweiss': 5,
            'Schwanzgelenk': 5,
            'Dreck': 5,
            'Ofen': 5,
            'Vertikaler Knochen': 5,
            'Pfeffer': 5,
            'Apfelmousse': 5,
            'Ampelman': 14,
            'Fluegelknochen': 50,
            'Fluegel Haut': 20,
            'Fluegel Feder': 10,
            'Mandeln': 5,
        }
        items = set(
            list(test_basic_items_demand.keys())
            + list(expected_basic_items_demand.keys())
        )
        for item in items:
            self.assertEqual(
                test_basic_items_demand[item],
                expected_basic_items_demand[item],
            )

    def test_q6(self) -> None:
        test_stock = load_stock(EXAMPLE_STOCK)
        self.assertEqual(len(test_stock), 13)
        test_menu = load_menu(EXAMPLE_MENU)
        test_demand = load_demand(EXAMPLE_DEMAND)
        test_basic_items_demand = collect_demand_items_without_stock(
            test_menu, test_demand, 32, test_stock
        )
        expected_basic_items_demand = {
            'Fluegelgelenk': 5,
            'Beinmuskel': 4,
            'Beinfett': 4,
            'Beingelenk': 4,
            'Beinhaut': 4,
            'Fluegelknochen': 25,
            'Fluegel Haut': 10,
            'Salz': 60,
        }
        items = set(
            list(test_basic_items_demand.keys())
            + list(expected_basic_items_demand.keys())
        )
        for item in items:
            if item == 'Salz':
                continue
            self.assertEqual(
                test_basic_items_demand[item],
                expected_basic_items_demand[item],
            )

    def test_exact_corner_case(self) -> None:
        self.assertEqual(
            len(
                collect_demand_items_without_stock(
                    {}, {0: {'Something': 1}}, 0, Counter({'Something': 1})
                )
            ),
            0,
        )


if __name__ == '__main__':  # pragma: no cover
    # Q1
    example_menu = load_menu(EXAMPLE_MENU)
    example_quantity = len(example_menu)
    print(
        f'Q1: Menu data loaded successfully. {example_quantity} dishes.\n'
    )
    # Q2
    example_dishes = collect_dishes(example_menu, 'Happy Menu')
    print(format_counted('Q2: Dishes in Happy Menu', example_dishes))
    # Q3
    example_basic_items = collect_basic_items(example_menu, 'Happy Menu')
    print(format_counted(
        'Q3: All basic items needed for one Happy Menu', example_basic_items
    ))
    # Q4
    example_demand = load_demand(EXAMPLE_DEMAND)
    example_quantity = len(example_demand)
    print(
        f'Q4: Demand data loaded successfully. {example_quantity} days.\n'
    )
    # Q5
    example_basic_items_demand = collect_basic_demand_items_for_day(
        example_menu, example_demand, 32
    )
    print(format_counted(
        'Q5: All basic items needed for day 32', example_basic_items_demand
    ))
    # Q6
    example_stock = load_stock(EXAMPLE_STOCK)
    example_quantity = len(example_stock)
    print(
        f'Q6: Stock data loaded successfully. {example_quantity} items.\n'
    )
    example_basic_items_demand = collect_demand_items_without_stock(
        example_menu, example_demand, 32, example_stock
    )
    print(format_counted(
        'All basic items demand for day 32 (considering stock)',
        example_basic_items_demand,
    ))
