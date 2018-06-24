package com.knrmalhotra.chapter4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView1, mTextView2;
    private ArrayList<String> mArrayList1, mArrayList2;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.knrmalhotra.chapter4.R.layout.activity_main);

        mTextView1 = (TextView) findViewById(R.id.text_view_main_one);
        mTextView2 = (TextView) findViewById(R.id.text_view_main_second);

        mArrayList1 = new ArrayList<>();
        mArrayList2 = new ArrayList<>();

        Observable<String> animalObservable = getAnimalObservable();

        DisposableObserver<String> animalDisposableObserver = getAnimalDisposableObserver();
        DisposableObserver<String> animalDisposableObserverAllCaps = getAnimalDisposableObserverAllCaps();

        mCompositeDisposable.add(animalObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.toLowerCase().startsWith("b");
                    }
                })
                .subscribeWith(animalDisposableObserver)
        );

        mCompositeDisposable.add(animalObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.toLowerCase().startsWith("b");
                    }
                })
                .map(new Function<String, String>() {
                    public String apply(String s) throws Exception{
                        return s.toUpperCase();
                    }
                })
                .subscribeWith(animalDisposableObserverAllCaps)

        );


    }

    private DisposableObserver<String> getAnimalDisposableObserver(){
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                mArrayList1.add(s);
                mTextView1.setText(mArrayList1.toString().replaceAll("[\\\\[\\\\](){}]",""));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private DisposableObserver<String> getAnimalDisposableObserverAllCaps(){
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                mArrayList2.add(s);
                mTextView2.setText(mArrayList2.toString().replaceAll("[\\\\[\\\\](){}]",""));
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All Data Emitted");
            }
        };
    }

    private Observable<String> getAnimalObservable(){
        return Observable.fromArray(
                "Ant", "Ape",
                "Bat", "Bee", "Bear", "Butterfly",
                "Cat", "Crab", "Cod",
                "Dog", "Dove",
                "Fox", "Frog"
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
