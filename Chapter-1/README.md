# Chapter 1: Introduction To Observable And Observer

## Adding Dependencies

To get started, you need to add the RxJava and RxAndroid dependencies to your projects build.gradle and sync the project.

```
// RxJava
implementation 'io.reactivex.rxjava2:rxjava:2.1.9'
 
// RxAndroid
implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
```

## The Basic Steps

1. Create an Observable that emits data. Below we have created an Observable that emits list of team names. Here **just()** operator is used to emit few team names.

```
Observable<String> animalsObservable = Observable.just("Brazil","Germany","England","France","Chile");
```
2. Create an **Observer** that listen to Observable. Observer provides the below interface methods to know the the state of Observable.

- **onSubscribe()**: Method will be called when an Observer subscribes to Observable.
- **onNext()**: This method will be called when Observable starts emitting the data.
- **onError()**: In case of any error, onError() method will be called.
- **onComplete()**: When an Observable completes the emission of all the items, onComplete() will be called.

```
private Observer<String> getTeamObserver(){
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                mArrayList.add(s);
                mTextView.setText(mArrayList.toString().replaceAll("[\\[\\](){}]",""));
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
    }
```

3. Make Observer subscribe to Observable so that it can start receiving the data. Here, you can notice two more methods, observeOn() and subscribeOn().

- **subscribeOn(Schedulers.io())**: This tell the Observable to run the task on a background thread.
- **observeOn(AndroidSchedulers.mainThread())**: This tells the Observer to receive the data on android UI thread so that you can take any UI related actions.

```
teamObservable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(teamObserver);
```
