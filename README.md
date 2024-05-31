# capacitor-insets

Capacitor plugin to control insets on Android and iOS devices.

## Install

```bash
npm install @namdevel/capacitor-insets
npx cap sync
```

## API

<docgen-index>

* [`getSafeAreaInsets()`](#getsafeareainsets)
* [`showStatusBar()`](#showstatusbar)
* [`hideStatusBar()`](#hidestatusbar)
* [`setStatusBarColor(...)`](#setstatusbarcolor)
* [`setStatusBarStyle(...)`](#setstatusbarstyle)
* [`isStatusBarVisible()`](#isstatusbarvisible)
* [`showNavigationBar()`](#shownavigationbar)
* [`hideNavigationBar()`](#hidenavigationbar)
* [`setNavigationBarColor(...)`](#setnavigationbarcolor)
* [`setNavigationBarStyle(...)`](#setnavigationbarstyle)
* [`isNavigationBarVisible()`](#isnavigationbarvisible)
* [`showKeyboard()`](#showkeyboard)
* [`hideKeyboard()`](#hidekeyboard)
* [`isKeyboardVisible()`](#iskeyboardvisible)
* [`getKeyboardInsets()`](#getkeyboardinsets)
* [`toggleAccessoryBar(...)`](#toggleaccessorybar)
* [`toggleScroll(...)`](#togglescroll)
* [`addListener('insets', ...)`](#addlistenerinsets-)
* [`addListener('keyboardshow', ...)`](#addlistenerkeyboardshow-)
* [`addListener('keyboardhide', ...)`](#addlistenerkeyboardhide-)
* [Interfaces](#interfaces)
* [Enums](#enums)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getSafeAreaInsets()

```typescript
getSafeAreaInsets() => Promise<Rect>
```

**Returns:** <code>Promise&lt;<a href="#rect">Rect</a>&gt;</code>

--------------------


### showStatusBar()

```typescript
showStatusBar() => Promise<void>
```

--------------------


### hideStatusBar()

```typescript
hideStatusBar() => Promise<void>
```

--------------------


### setStatusBarColor(...)

```typescript
setStatusBarColor(options: ColorOptions) => Promise<void>
```

| Param         | Type                                                  |
| ------------- | ----------------------------------------------------- |
| **`options`** | <code><a href="#coloroptions">ColorOptions</a></code> |

--------------------


### setStatusBarStyle(...)

```typescript
setStatusBarStyle(options: StyleOptions) => Promise<void>
```

| Param         | Type                                                  |
| ------------- | ----------------------------------------------------- |
| **`options`** | <code><a href="#styleoptions">StyleOptions</a></code> |

--------------------


### isStatusBarVisible()

```typescript
isStatusBarVisible() => Promise<boolean>
```

**Returns:** <code>Promise&lt;boolean&gt;</code>

--------------------


### showNavigationBar()

```typescript
showNavigationBar() => Promise<void>
```

--------------------


### hideNavigationBar()

```typescript
hideNavigationBar() => Promise<void>
```

--------------------


### setNavigationBarColor(...)

```typescript
setNavigationBarColor(options: ColorOptions) => Promise<void>
```

| Param         | Type                                                  |
| ------------- | ----------------------------------------------------- |
| **`options`** | <code><a href="#coloroptions">ColorOptions</a></code> |

--------------------


### setNavigationBarStyle(...)

```typescript
setNavigationBarStyle(options: StyleOptions) => Promise<void>
```

| Param         | Type                                                  |
| ------------- | ----------------------------------------------------- |
| **`options`** | <code><a href="#styleoptions">StyleOptions</a></code> |

--------------------


### isNavigationBarVisible()

```typescript
isNavigationBarVisible() => Promise<boolean>
```

**Returns:** <code>Promise&lt;boolean&gt;</code>

--------------------


### showKeyboard()

```typescript
showKeyboard() => Promise<void>
```

--------------------


### hideKeyboard()

```typescript
hideKeyboard() => Promise<void>
```

--------------------


### isKeyboardVisible()

```typescript
isKeyboardVisible() => Promise<boolean>
```

**Returns:** <code>Promise&lt;boolean&gt;</code>

--------------------


### getKeyboardInsets()

```typescript
getKeyboardInsets() => Promise<Rect>
```

**Returns:** <code>Promise&lt;<a href="#rect">Rect</a>&gt;</code>

--------------------


### toggleAccessoryBar(...)

```typescript
toggleAccessoryBar(options: ToggleOptions) => Promise<void>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#toggleoptions">ToggleOptions</a></code> |

--------------------


### toggleScroll(...)

```typescript
toggleScroll(options: ToggleOptions) => Promise<void>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#toggleoptions">ToggleOptions</a></code> |

--------------------


### addListener('insets', ...)

```typescript
addListener(eventName: 'insets', listener: (event: InsetsEvent) => void) => Promise<void>
```

| Param           | Type                                                                    |
| --------------- | ----------------------------------------------------------------------- |
| **`eventName`** | <code>'insets'</code>                                                   |
| **`listener`**  | <code>(event: <a href="#insetsevent">InsetsEvent</a>) =&gt; void</code> |

--------------------


### addListener('keyboardshow', ...)

```typescript
addListener(eventName: 'keyboardshow', listener: (insets: Rect) => void) => Promise<void>
```

| Param           | Type                                                       |
| --------------- | ---------------------------------------------------------- |
| **`eventName`** | <code>'keyboardshow'</code>                                |
| **`listener`**  | <code>(insets: <a href="#rect">Rect</a>) =&gt; void</code> |

--------------------


### addListener('keyboardhide', ...)

```typescript
addListener(eventName: 'keyboardhide', listener: () => void) => Promise<void>
```

| Param           | Type                        |
| --------------- | --------------------------- |
| **`eventName`** | <code>'keyboardhide'</code> |
| **`listener`**  | <code>() =&gt; void</code>  |

--------------------


### Interfaces


#### Rect

| Prop         | Type                |
| ------------ | ------------------- |
| **`top`**    | <code>number</code> |
| **`right`**  | <code>number</code> |
| **`bottom`** | <code>number</code> |
| **`left`**   | <code>number</code> |


#### ColorOptions

| Prop        | Type                |
| ----------- | ------------------- |
| **`color`** | <code>string</code> |


#### StyleOptions

| Prop        | Type                                    |
| ----------- | --------------------------------------- |
| **`style`** | <code><a href="#style">Style</a></code> |


#### ToggleOptions

| Prop          | Type                 |
| ------------- | -------------------- |
| **`enabled`** | <code>boolean</code> |


#### InsetsEvent

| Prop         | Type                                              |
| ------------ | ------------------------------------------------- |
| **`type`**   | <code><a href="#insetstype">InsetsType</a></code> |
| **`insets`** | <code><a href="#rect">Rect</a></code>             |


### Enums


#### Style

| Members       | Value                  |
| ------------- | ---------------------- |
| **`Dark`**    | <code>'dark'</code>    |
| **`Light`**   | <code>'light'</code>   |
| **`Default`** | <code>'default'</code> |


#### InsetsType

| Members        | Value                    |
| -------------- | ------------------------ |
| **`SafeArea`** | <code>"safe-area"</code> |
| **`Keyboard`** | <code>"keyboard"</code>  |

</docgen-api>
