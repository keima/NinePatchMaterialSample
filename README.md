NinePatchViewOutlineProvider
=======================

# これは何？

Android5.0から追加されたElevationを使うと美麗な影エフェクトをViewに付けることができるのですが、
9patchをつかって複雑な図形にしていると、うまく影が生成されないので、
気に入らないからリフレクションを使って無理矢理解決するテスト。

# わかったこと

- 影は `Outline`クラスの値から算出される
 - Rect(矩形、四角形), Round(角の丸み), Pathクラスがつかえる
 - Path.isConvexPath() == false だとIllegalArgumentExceptionがスロー(使えない)
  - isConvex、よくわからんけど、丸とか楕円とかRoundRectみたいな、常に同じ向きに曲がる図形ということかな？
  - だから星形とか吹き出しは、とんがり部分で反対方向に曲がるから駄目っぽい？
   - <u>もし星形の影をつけることができた人がいたら教えて＞＜</u>
- ふつうのViewでは自動生成される
 - ただそれだとバリエーションが少ないので、`android:outlineProvider`で4種類くらい指定できる
  - none : Outlineを生成しない
  - background(デフォルト) : ViewOutlineProvider.BACKGROUND の生成式
   - 基本的にbackgroundに指定されているDrawableに実装されているgetOutline(Outline)メソッドから生成
  - bounds : Viewをきっちり使う(Viewの左上を起点、右下を終点のRect生成)
  - paddedBounds : ViewのPadding値をつかう
- さらにいぢりたい人向けに、View.setOutlineProvider(ViewOutlineProvider)が用意されている
 - 引数に独自定義のViewOutlineProvider abstractクラスを指定すればOK

というわけで、このgithubでは、独自定義のViewOutlineProviderで吹き出しの9patch(`ic_message.9.png`)に指定されている
ViewBounds値を考慮したOutline生成を行っています。

 
