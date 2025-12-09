# Menu

## Q1

For a restaurant one part of the operations is managing your inventory - knowing how many ingredients you need (and how much you have) is vital to keeping it manageable!

A grouping of ingredients that have been prepared is called an _dish_.

Each menu we serve comes with a list of ingredients, which details which dish each ingredient is in, and how many of these ingredients are needed to create that dish. This is typically presented in the format of "dish", "item", and "quantity", e.g.:

```
dish,item,quantity
Kaiserschmarrn,Apfelmousse,1
```

In this question, you are provided with a fictional list of ingredients for a fictional menu called the Happy Menu. Our goal is to understand how many of each ingredient is required in total to prepare a single dish in different scenarios. We'll split this question into multiple parts.

Our first goal is the following: **Please read in the provided list of ingredients and load it into an appropriate data structure**. The list of ingredients can be found in `menu.csv`.

## Q2

We want to prepare a single "Happy Menu". What are the immediate dishes of one Happy Menu? How many of each do we need?

_Example: There are 4 x Haxengroestl in the preparation of a Happy Menu._

## Q3

Extend your solution to calculate the quantity of each "ingredient" required to make one "Happy Menu". An ingredient is a part that is not composed of other ingredients - i.e. a "raw" ingredient.

Don't be afraid to refactor your data structure from question 1 if you need to.

_Example: There are 60 x Salt in an entire Happy Menu_

## Q4

It's common to track the demand for each ingredient on a day-by-day basis. Demand is tracked in the following format:

```
item,demand,day
Schnitzel,2,32
```

In this example, it expresses the need for 2 "Schnitzel" on day 32.

Please parse in the weekly demand data located at `demand.csv` into a suitable data structure.

## Q5

What is the total quantity of each ingredient required to fulfill the demand for day 32?

_Example: There are 316 x Salt needed in day 32_

## Q6

In addition to tracking the list of ingredients and the demand for each day, we also track the amount of stock we currently have of each ingredient and dish. This is tracked in the following format:

```
item,quantity
Schnitzel,10
```

Note that we may have both ingredients AND dishes in stock at any point in time.

Please parse in the current stock from `stock.csv`.

Using those stock numbers, please calculate the actual demand for each ingredient on day 32.

Actual demand is calculated in the same way as regular demand, except if a dish or ingredient is in stock, use that dish or ingredient instead of creating a new one.

_Example: Take Kalbsleber Berliner Art: even though there's demand for 5 x Happy Menus and each Happy Menu has 4 x Geschmorter Ochsenschwanz (so 20 Kalbsleber Berliner Art in total), because we have 16 x Geschmorter Ochsenschwanz in stock, we only need 4 x Geschmorter Ochsenschwanz, and thus only 4 x Kalbsleber Berliner Art_.

Note that we may have both ingredients AND their dishes in stock at any point in time.

## Assessment

- 3 - finished or nearly finished Q4
- 3+ - finished Q4, starting Q5
- 3++ - finished Q5
- 4 - (some) Q6