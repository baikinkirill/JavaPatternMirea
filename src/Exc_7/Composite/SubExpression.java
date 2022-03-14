package Exc_7.Composite;

// Единообразный интерфейс доступа к листовым и контейнерным объектам
public interface SubExpression {

    Number value();

    void add(SubExpression expr);

    void sub(SubExpression expr);

    SubExpression getSubExpression(int index);
}





