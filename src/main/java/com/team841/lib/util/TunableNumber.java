package com.team841.lib.util;

import edu.wpi.first.networktables.*;

public class TunableNumber {
  private static final String tableKey = "TunableNumbers";

  private final NetworkTableInstance inst = NetworkTableInstance.getDefault();
  private final NetworkTable ntTunable = inst.getTable(tableKey);

  private final DoublePublisher publisher;
  private final DoubleSubscriber subscriber;

  private double value;

  /**
   * Create a new LoggedTunableNumber
   *
   * @param key Key on dashboard
   */
  public TunableNumber(String key) {
    DoubleTopic topic = ntTunable.getDoubleTopic(key);
    this.publisher = topic.publish();
    this.subscriber = topic.subscribe(0);
  }

  /**
   * Create a new LoggedTunableNumber with the default value
   *
   * @param key Key on dashboard
   * @param startingValue Default value
   */
  public TunableNumber(String key, double startingValue) {
    this(key);
    this.value = startingValue;
    this.publisher.set(startingValue);
  }

  /**
   * Get the current value, from dashboard if available and in tuning mode.
   *
   * @return The current value
   */
  public double get() {
    return this.subscriber.get();
  }

  /**
   * Checks whether the number has changed since our last check
   *
   * @return True if the number has changed since the last time this method was called, false
   *     otherwise.
   */
  public boolean hasChanged() {
    if (this.subscriber.get() != this.value) {
      this.value = this.subscriber.get();
      return true;
    }
    return false;
  }
}
