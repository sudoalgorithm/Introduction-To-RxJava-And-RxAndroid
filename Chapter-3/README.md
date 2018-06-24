# Chapter 3: Introduction To Operator

Now we’ll see another example by introducing an operator to transform the emitted data. In the below example **filter()** operator is used to filter out the emitted data.

- **filter()** operator filters the data by applying a conditional statement. The data which meets the condition will be emitted and the remaining will be ignored.

In the below example the teans names which starts with letter "b" will be filtered.

```
teamObservable
.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.toLowerCase().startsWith("b");
                    }
                })
                .subscribeWith(animalsObserver);
}
```