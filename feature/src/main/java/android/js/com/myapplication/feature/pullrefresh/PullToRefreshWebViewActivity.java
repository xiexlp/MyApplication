/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package android.js.com.myapplication.feature.pullrefresh;

import android.app.Activity;
import android.js.com.myapplication.feature.R;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshWebView;

public final class PullToRefreshWebViewActivity extends Activity {

	PullToRefreshWebView mPullRefreshWebView;
	WebView mWebView;
	private TextView tv_title;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ptr_webview);


		tv_title = findViewById(R.id.tv_title);
		tv_title.setText("网页下拉刷新");

		mPullRefreshWebView = (PullToRefreshWebView) findViewById(R.id.pull_refresh_webview);
		mWebView = mPullRefreshWebView.getRefreshableView();

		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new SampleWebViewClient());
		mWebView.loadUrl("https://www.baidu.com");




	}

	private static class SampleWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

}
