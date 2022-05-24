# User Guide
Stack the blocks. Don't let them reach the top. If an entire line is filled, it will be cleared.
## Controls
- A : Move Left
- D : Move Right
- S : Soft Drop
- Q : Rotate Counterclockwise
- E : Rotate Clockwise
- [SPACE] : Hard Drop
## Scoring
- Soft Drop: 1 x level x distance
- Hard Drop: 2 x level x distance
- Single: 100 x level
- Double: 300 x level
- Triple: 500 x level
- Tetris: 800 x level
- Back-To-Back Tetris : 1200 x level
- Combos: 50 x combo x level
# Miscellania
Overall thoughts I had about implementing this game.
## Swing
Swing sucks. Avoid the built in `RepaintManager` as much as possible. Can't even render a static component at 60 fps, smh.
## Java2D
My Lord and Savior. Make sure to enable OpenGL when using OpenJDK (Oracle JDK uses some proprietary blobs for rendering). Use `java -Dsun.java2d.opengl=true Main` for running. Make sure to use `Toolkit.getDefaultToolkit().sync()` to flush the rendering pipeline. Flickering is a often a result of latencies that come from the Java API's. Modern computers should easily run these sorts of games at >60 FPS. 
## Java 
Very verbose language. OOP can be very cargo culty at times. AP Computer Science? more like AP Java amirite.