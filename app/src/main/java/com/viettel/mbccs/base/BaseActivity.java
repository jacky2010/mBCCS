package com.viettel.mbccs.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.viettel.mbccs.dialog.LoadingDialog;
import java.util.Stack;

public abstract class BaseActivity extends AppCompatActivity {

    private FragmentManager mFrgManager;
    protected Stack<BaseFragment> mFragStack;
    protected LoadingDialog mLoadingDialog;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    protected abstract int getIdLayout();

    protected abstract void initData();

    protected void initDataBinder() {
        setContentView(getIdLayout());
        mUnbinder = ButterKnife.bind(this);
    }

    /**
     * Show loading dialog without leak window
     */
    public void showLoadingDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing() || (mLoadingDialog != null && mLoadingDialog.isAdded())) {
                    return;
                }

                if (mLoadingDialog == null) {
                    mLoadingDialog = new LoadingDialog();
                    mLoadingDialog.setCancelable(true);
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                mLoadingDialog.show(fragmentManager, "loading");
            }
        });
    }

    public void showLoadingDialog(final boolean cancelable) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing() || (mLoadingDialog != null && mLoadingDialog.isAdded())) {
                    return;
                }

                if (mLoadingDialog == null) {
                    mLoadingDialog = new LoadingDialog();
                    mLoadingDialog.setCancelable(cancelable);
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                mLoadingDialog.show(fragmentManager, "loading");
            }
        });
    }

    /**
     * Hide loading dialog, with check activity working or not
     */
    public void hideLoadingDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing() || mLoadingDialog == null || !mLoadingDialog.isAdded()) {
                    return;
                }
                mLoadingDialog.dismiss();
            }
        });
    }

    //call fragment or dialog fragment

    public void goToDialogFragment(BaseDialog mBaseDialog, Bundle mBundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mBaseDialog.setArguments(mBundle);
        mBaseDialog.show(fragmentManager, mBaseDialog.getClass().getName());
    }

    public void goToFragment(BaseFragment mBaseFragment, Bundle mBundle) {
        if (mFragStack == null && mFrgManager == null) {
            mFragStack = new Stack();
            mFrgManager = getSupportFragmentManager();
        }
        FragmentTransaction trans = mFrgManager.beginTransaction();
        if (mBundle != null) {
            mBaseFragment.setArguments(mBundle);
        }
        if (mFragStack != null && mFragStack.size() >= 1) {
            trans.hide(mFragStack.lastElement());
        }
        mFragStack.push(mBaseFragment);
        trans.add(idContainerFragment(), mBaseFragment,
                mBaseFragment.getClass().getSimpleName());
        trans.commit();
        mFrgManager.executePendingTransactions();
    }

    public void onBackFragment() {
        FragmentTransaction trans = mFrgManager.beginTransaction();
        if (mFragStack.size() > 1) {
            trans.remove(mFragStack.pop());
            trans.show(mFragStack.lastElement());
            mFragStack.lastElement().onResume();
            trans.commit();
        } else {
            finish();
        }
    }

    protected int idContainerFragment() {
        return 0;
    }

    public void setTitleToolbar(int idTitle) {

    }

    //call activity other

    public void openActivityForResult(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    public void openActivity(Class<?> pClass, boolean isFinish) {
        openActivity(pClass);
        if (isFinish) {
            finish();
        }
    }

    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }
}
