# Chapter 2: Introduction To Disposable

In this we gonna learn new component called **Disposable**.

- **Disposable**: Disposable is used to dispose the subscription when an Observer no longer wants to listen to Observable. In android disposable are very useful in avoiding memory leaks.

- Let’s say you are making a long running network call and updating the UI. By the time network call completes its work, if the activity / fragment is already destroyed, as the Observer subscription is still alive, it tries to update already destroyed activity. In this case it can throw a memory leak. So using the Disposables, the un-subscription can be when the activity is destroyed.

In the below code snippet you can see a **Disposable** is used and calling **disposable.dispose()** in **onDestroy()** will **un-subscribe** the Observer.

```
private Disposable mDisposable;
```
```
private Observer<String> getTeamObserver(){
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                mDisposable = d;
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
```
private Observable<String> getTeamObservable(){
        return Observable.just("Brazil","Germany","England","France","Chile");
}
```
```
@Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }
```