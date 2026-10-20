package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

@ExtendWith(SystemStubsExtension.class)
class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void invalid_firing_zero_torpedos() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);
        boolean gotError = false;

        // Act
        try{
            store.fire(0);
        } catch (IllegalArgumentException e) {
            gotError = true;
        }

        // Assert
        assertEquals(true, gotError);
    }

    @Test
    void invalid_firing_many_torpedos() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);
        boolean gotError = false;

        // Act
        try{
            store.fire(2);
        } catch (IllegalArgumentException e) {
            gotError = true;
        }

        // Assert
        assertEquals(true, gotError);
    }

    @Test
    void TorpedoCount_getter() {
        // Arrange
        TorpedoStore store = new TorpedoStore(10);

        // Act
        int result = store.getTorpedoCount();

        // Assert
        assertEquals(10, result);
    }

    @SystemStub
    private EnvironmentVariables environmentVariables;

    @Test
    void Failure_rate_set_in_env_var() {
        // Arrange
        environmentVariables.set("IVT_RATE", "0.5");
        
        // Act
        TorpedoStore store = new TorpedoStore(10);

        // Assert
        assertEquals(0.5, store.getFailure_Rate());
    }

    @Test
    void Failure_rate_set_in_env_var_fail() {
        // Arrange
        environmentVariables.set("IVT_RATE", "ABCD");
        
        // Act
        TorpedoStore store = new TorpedoStore(10);

        // Assert
        assertEquals(0.0, store.getFailure_Rate());
    }
}
