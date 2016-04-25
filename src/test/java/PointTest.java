import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PointTest {

    @Test
    public void distance_should_return_zero_when_points_are_identical() {
        //Given
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 0);

        //When
        double distance = pointA.distance(pointB);

        //Then
        assertThat(distance).isEqualTo(0);
    }

    @Test
    public void distance_should_return_absisse_when_first_point_is_origin_and_second_on_absisse_axe() {
        //Given
        Point pointA = new Point(0, 0);
        Point pointB = new Point(5, 0);

        //When
        double distance = pointA.distance(pointB);

        //Then
        assertThat(distance).isEqualTo(5);
    }

    @Test
    public void distance_should_return_ordonate_when_first_point_is_origin_and_second_on_ordinate_axe() {
        //Given
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 5);

        //When
        double distance = pointA.distance(pointB);

        //Then
        assertThat(distance).isEqualTo(5);
    }

    @Test
    public void distance_should_return_absolute_value_of_xs_difference_when_points_are_on_the_same_x_axe() {
        //Given
        Point pointA = new Point(4, 5);
        Point pointB = new Point(6, 5);

        //When
        double distance = pointA.distance(pointB);

        //Then
        assertThat(distance).isEqualTo(2);
    }

    @Test
    public void distance_should_return_absolute_value_of_ys_difference_when_points_are_on_the_same_y_axe() {
        //Given
        Point pointA = new Point(5, 6);
        Point pointB = new Point(5, 4);

        //When
        double distance = pointA.distance(pointB);

        //Then
        assertThat(distance).isEqualTo(2);
    }

    @Test
    public void distance_should_return_5_for_3_4_5_triangle() {
        //Given
        Point pointA = new Point(0, 3);
        Point pointB = new Point(4, 0);

        //When
        double distance = pointA.distance(pointB);

        //Then
        assertThat(distance).isEqualTo(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void distance_should_throw_illegal_argument_exeption_when_point2_is_null() {
        //Given
        Point pointA = new Point(0, 3);
        Point pointB = null;

        //When
        pointA.distance(pointB);

    }
}
