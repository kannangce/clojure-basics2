(ns basics-2.ex_04_on_macros)

;; Quote - Lets you have an expression unevaluated

(quote (+ 1 2))

(eval (quote (+ 1 2)))

(eval '(+ 1 2))

;; Lets play around with contorl of evaluation
 
(def some-string nil)

(when-not (nil? some-string) (. some-string length))

(defn when-not-fn
  [predicate expr]
  (if (not predicate) (eval expr) nil))

(when-not-fn (nil? some-string) '(. some-string length))


;; So now, you have taken over the control of when the parameter is expected 
;; to be executed. What if the let the language take control over it.

;; And that is macro.

(defmacro my-when-not
  [predicate expr]
  `(if ~(not predicate) ~expr nil))

;; TODO - Difference between ' and `

(macroexpand '(my-when-not (nil? some-string) (. some-string length)))


;; In-fix expression evaluator
(defmacro in-fix-op
  [expr]
  `(~(second expr)
    ~(first expr)
    ~(last expr)))

(in-fix-op (2 - 1))

;; Like this, macro allows you to turn any non-clojure syntax to 
;; clojure to syntax