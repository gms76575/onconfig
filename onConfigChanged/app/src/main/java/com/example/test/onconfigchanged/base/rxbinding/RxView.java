package com.example.test.onconfigchanged.base.rxbinding;

/**
 * Created by xn052805 on 2017/4/4.
 */

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

import static com.example.test.onconfigchanged.base.rxbinding.Preconditions.checkNotNull;
import static com.example.test.onconfigchanged.base.rxbinding.Preconditions.checkArgument;

/**
 * Static factory methods for creating {@linkplain Observable observables} and {@linkplain Action1
 * actions} for {@link View}.
 */
public final class RxView {
    /**
     * Create an observable which emits on {@code view} click events. The emitted value is
     * unspecified and should only be used as notification.
     * <p/>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     * <p/>
     * <em>Warning:</em> The created observable uses {@link View#setOnClickListener} to observe
     * clicks. Only one observable can be used for a view at a time.
     */
    @CheckResult
    @NonNull
    public static Observable<Void> clicks(@NonNull View view) {
        checkNotNull(view, "view == null");
        return Observable.create(new ViewClickOnSubscribe(view));
    }

    /**
     * Create an observable which emits on {@code view} long-click events. The emitted value is
     * unspecified and should only be used as notification.
     * <p/>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     * <p/>
     * <em>Warning:</em> The created observable uses {@link View#setOnLongClickListener} to observe
     * long clicks. Only one observable can be used for a view at a time.
     */
    @CheckResult
    @NonNull
    public static Observable<Void> longClicks(@NonNull View view) {
        checkNotNull(view, "view == null");
        return Observable.create(new ViewLongClickOnSubscribe(view, Functions.FUNC0_ALWAYS_TRUE));
    }

    /**
     * Create an observable which emits on {@code view} long-click events. The emitted value is
     * unspecified and should only be used as notification.
     * <p/>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     * <p/>
     * <em>Warning:</em> The created observable uses {@link View#setOnLongClickListener} to observe
     * long clicks. Only one observable can be used for a view at a time.
     *
     * @param handled Function invoked each occurrence to determine the return value of the
     *                underlying {@link View.OnLongClickListener}.
     */
    @CheckResult
    @NonNull
    public static Observable<Void> longClicks(@NonNull View view, @NonNull Func0<Boolean> handled) {
        checkNotNull(view, "view == null");
        checkNotNull(handled, "handled == null");
        return Observable.create(new ViewLongClickOnSubscribe(view, handled));
    }

    /**
     * Create an observable for pre-draws on {@code view}.
     * <p/>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     * <p/>
     * <em>Warning:</em> The created observable uses {@link ViewTreeObserver#addOnPreDrawListener} to
     * observe pre-draws. Multiple observables can be used for a view at a time.
     */
    @CheckResult
    @NonNull
    public static Observable<Void> preDraws(@NonNull View view,
                                            @NonNull Func0<Boolean> proceedDrawingPass) {
        checkNotNull(view, "view == null");
        checkNotNull(proceedDrawingPass, "proceedDrawingPass == null");
        return Observable.create(new ViewTreeObserverPreDrawOnSubscribe(view, proceedDrawingPass));
    }


    /**
     * Create an observable of touch events for {@code view}.
     * <p/>
     * <em>Warning:</em> Values emitted by this observable are <b>mutable</b> and part of a shared
     * object pool and thus are <b>not safe</b> to cache or delay reading (such as by observing
     * on a different thread). If you want to cache or delay reading the items emitted then you must
     * map values through a function which calls {@link MotionEvent#obtain(MotionEvent)} or
     * {@link MotionEvent#obtainNoHistory(MotionEvent)} to create a copy.
     * <p/>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     * <p/>
     * <em>Warning:</em> The created observable uses {@link View#setOnTouchListener} to observe
     * touches. Only one observable can be used for a view at a time.
     */
    @CheckResult
    @NonNull
    public static Observable<MotionEvent> touches(@NonNull View view) {
        checkNotNull(view, "view == null");
        return touches(view, Functions.FUNC1_ALWAYS_TRUE);
    }

    /**
     * Create an observable of touch events for {@code view}.
     * <p/>
     * <em>Warning:</em> Values emitted by this observable are <b>mutable</b> and part of a shared
     * object pool and thus are <b>not safe</b> to cache or delay reading (such as by observing
     * on a different thread). If you want to cache or delay reading the items emitted then you must
     * map values through a function which calls {@link MotionEvent#obtain(MotionEvent)} or
     * {@link MotionEvent#obtainNoHistory(MotionEvent)} to create a copy.
     * <p/>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     * <p/>
     * <em>Warning:</em> The created observable uses {@link View#setOnTouchListener} to observe
     * touches. Only one observable can be used for a view at a time.
     *
     * @param handled Function invoked with each value to determine the return value of the
     *                underlying {@link View.OnTouchListener}.
     */
    @CheckResult
    @NonNull
    public static Observable<MotionEvent> touches(@NonNull View view,
                                                  @NonNull Func1<? super MotionEvent, Boolean> handled) {
        checkNotNull(view, "view == null");
        checkNotNull(handled, "handled == null");
        return Observable.create(new ViewTouchOnSubscribe(view, handled));
    }

    /**
     * Create an observable of booleans representing the focus of {@code view}.
     * <p/>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     * <p/>
     * <em>Warning:</em> The created observable uses {@link View#setOnFocusChangeListener} to observe
     * focus change. Only one observable can be used for a view at a time.
     * <p/>
     * <em>Note:</em> A value will be emitted immediately on subscribe.
     */
    @CheckResult
    @NonNull
    public static Observable<Boolean> focusChanges(@NonNull View view) {
        checkNotNull(view, "view == null");
        return Observable.create(new ViewFocusChangeOnSubscribe(view));
    }

    /**
     * An action which sets the clickable property of {@code view}.
     * <p/>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult
    @NonNull
    public static Action1<? super Boolean> clickable(@NonNull final View view) {
        checkNotNull(view, "view == null");
        return new Action1<Boolean>() {
            @Override
            public void call(Boolean value) {
                view.setClickable(value);
            }
        };
    }

    /**
     * An action which sets the enabled property of {@code view}.
     * <p/>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult
    @NonNull
    public static Action1<? super Boolean> enabled(@NonNull final View view) {
        checkNotNull(view, "view == null");
        return new Action1<Boolean>() {
            @Override
            public void call(Boolean value) {
                view.setEnabled(value);
            }
        };
    }


    /**
     * An action which sets the visibility property of {@code view}. {@code false} values use
     * {@code View.GONE}.
     * <p/>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult
    @NonNull
    public static Action1<? super Boolean> visibility(@NonNull View view) {
        checkNotNull(view, "view == null");
        return visibility(view, View.GONE);
    }

    /**
     * An action which sets the visibility property of {@code view}.
     * <p/>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     *
     * @param visibilityWhenFalse Visibility to set on a {@code false} value ({@code View.INVISIBLE}
     *                            or {@code View.GONE}).
     */
    @CheckResult
    @NonNull
    public static Action1<? super Boolean> visibility(@NonNull final View view,
                                                      final int visibilityWhenFalse) {
        checkNotNull(view, "view == null");
        checkArgument(visibilityWhenFalse != View.VISIBLE,
                "Setting visibility to VISIBLE when false would have no effect.");
        checkArgument(visibilityWhenFalse == View.INVISIBLE || visibilityWhenFalse == View.GONE,
                "Must set visibility to INVISIBLE or GONE when false.");
        return new Action1<Boolean>() {
            @Override
            public void call(Boolean value) {
                view.setVisibility(value ? View.VISIBLE : visibilityWhenFalse);
            }
        };
    }

    private RxView() {
        throw new AssertionError("No instances.");
    }
}
