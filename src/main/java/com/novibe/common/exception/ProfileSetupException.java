package com.novibe.common.exception;

import lombok.experimental.StandardException;

/**
 * Something went wrong with a single profile, but the rest of the profiles can still be set up.
 * Such failures are collected in {@link com.novibe.common.util.ProfileFailures} and reported at the end of the run.
 */
@StandardException
public abstract class ProfileSetupException extends RuntimeException {
}
