package at.ac.tuwien.sportmate;

/***
 * Interface for the SlideToStartActivity control. 
 * This interface provides one method which shall be called
 * when the slide action was successfully performed to the end 
 * of the slider and a method which indicates when a slides has 
 * been canceled or performed incorrectly (e.g. out of the slider range). 
 * @author eduardmargulies
 *
 */
public interface ISlideToStartActivity {
	/***
	 * Shall be called when the slider-knob reached the end of the slider.
	 */
	public void SlidePerformed();
	/***
	 * Shall be called when the slide action could not be finished.
	 */
	public void SlideCanceled();

}
